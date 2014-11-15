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
import sg.edu.nus.iss.ems.util.EncryptionUtils;
import sg.edu.nus.iss.ems.util.JsfUtil;

@ViewScoped
@Named
public class ModuleMgmtView implements Serializable {
    
    @EJB ModuleMgmtService moduleBean;
    
    private Module selectedModule;
    private List<Module> modules;
    
    // setters & getters
    public Module getSelectedModule() {
        return selectedModule;
    }

    public void setSelectedModule(Module selectedModule) {
        this.selectedModule = selectedModule;
    }
    
    public List<Module> getModules() {
        //if (modules == null)
            modules = moduleBean.findAll();
        return modules;
    }
    
    // CRUD methods
    public Module prepareCreate() {
        selectedModule = new Module();
        return selectedModule;
    }
    
    public void create() {
        moduleBean.create(selectedModule);
        if (!JsfUtil.isValidationFailed()) {
            modules = null;    // Invalidate list of modules to trigger re-query.
        }
    }
    
    public void detele() {
        moduleBean.delete(selectedModule);
        if (!JsfUtil.isValidationFailed()) {
            selectedModule = null; // Remove selection
            modules = null;    // Invalidate list of modules to trigger re-query.
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
                    if(tempArr.length == 2) {
                        String moduleCode = tempArr[0];
                        //Check whether use existing
                        if(moduleBean.isModuleExisted(moduleCode)){
                            String name = tempArr[1];
                            Module module = new Module();
                           module.setCode(moduleCode);
                           module.setName(name);
                           module.setStatus(1);
                           moduleBean.create(module);
                            System.out.println(">> module created : " + module.getCode());
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
