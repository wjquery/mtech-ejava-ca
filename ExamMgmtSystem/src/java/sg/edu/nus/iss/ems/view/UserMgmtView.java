package sg.edu.nus.iss.ems.view;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sg.edu.nus.iss.ems.entity.User;
import sg.edu.nus.iss.ems.service.UserMgmtService;
import sg.edu.nus.iss.ems.util.JsfUtil;

@ViewScoped
@Named
public class UserMgmtView implements Serializable {
    
    @EJB
    private UserMgmtService userBean;
    
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
    
}
