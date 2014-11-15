package sg.edu.nus.iss.ems.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;
import sg.edu.nus.iss.ems.entity.Module;
import sg.edu.nus.iss.ems.entity.Role;
import sg.edu.nus.iss.ems.entity.User;
import sg.edu.nus.iss.ems.service.ModuleMgmtService;
import sg.edu.nus.iss.ems.service.UserMgmtService;
import sg.edu.nus.iss.ems.util.EncryptionUtils;
import sg.edu.nus.iss.ems.util.JsfUtil;

@ViewScoped
@Named
public class UserMgmtView implements Serializable {
    
    @EJB
    private UserMgmtService userBean;
    
    @EJB ModuleMgmtService moduleBean;
    
    private List<User> users = null;
    private User selectedUser;
    
    // setters & getters
    public List<User> getUsers() {
        if (users == null) {
            users = userBean.findAll();
        }
        return users;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
    
    // CRUD methods
    public User prepareCreate() {
        selectedUser = new User();
        return selectedUser;
    }
    
    public void create() {
        userBean.create(selectedUser);
        if (!JsfUtil.isValidationFailed()) {
            users = null;    // Invalidate list of users to trigger re-query.
        }
    }
    
    public void delete() {
        userBean.delete(selectedUser);
        if (!JsfUtil.isValidationFailed()) {
            selectedUser = null; // Remove selection
            users = null;    // Invalidate list of users to trigger re-query.
        }
    }
    
    
    private UploadedFile file;
 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() {
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            //read the files
            //Note, do not use file.getContents() as it will always return null
            BufferedReader bfReader = null;
            InputStream is = null;
            
            try {
                is =  file.getInputstream();
                bfReader = new BufferedReader(new InputStreamReader(is));
                String temp = null;
                //skip first line bfReader.readLine() username, Name, role
                bfReader.readLine();
                while((temp = bfReader.readLine()) != null){
                    String[] tempArr = temp.split(",");
                    if(tempArr.length == 4) {
                        String username = tempArr[0];
                        //Check whether use existing
                        if(userBean.isUserExisted(username)){
                            String name = tempArr[1];
                            String roleName = tempArr[2];
                            String moduleCodes = tempArr[3]; //comma Seperated
                            User user = new User();
                            user.setUsername(username);
                            user.setName(name);
                            user.setStatus(User.USER_STATUS_NEW);
                            user.setPassword(EncryptionUtils.encryt(username, username));
                            Set<Role> roles = new HashSet<>();
                            if("lecturer".equalsIgnoreCase(roleName)){
                                Role lectureRole = userBean.loadRole(2); //ugly
                                //lectureRole.getUsers().add(user);
                                roles.add(lectureRole);
                                
                            }else if("student".equalsIgnoreCase("student")){
                                Role studentRole = userBean.loadRole(3); //ugly
                                //studentRole.getUsers().add(user);
                                roles.add(studentRole);
                            }
                            String[] modArr = moduleCodes.split(";");
                            List<Module> modules = new ArrayList<>();
                            for(String mod : modArr) {
                                Module module = moduleBean.load(mod);
                                modules.add(module);
                            }
                            user.setModules(modules);
                            user.setRoles(roles);
                            userBean.create(user);
                            System.out.println(">> user created : " + user.getId());
                        }else{
                            //HOW TO DEAL with exisiting user, ignore for now
                        }
                    }else{
                        //invalid line, ignore?
                        System.out.println(">> invalid line: " + temp);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try{
                    if(is != null) is.close();
                } catch (Exception ex){
                }
            }
        }
    }
    
}
