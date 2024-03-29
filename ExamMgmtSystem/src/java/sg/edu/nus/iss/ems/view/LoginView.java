package sg.edu.nus.iss.ems.view;

import java.io.Serializable;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import sg.edu.nus.iss.ems.entity.Role;
import sg.edu.nus.iss.ems.entity.User;
import sg.edu.nus.iss.ems.service.UserAccountService;

@Named
@SessionScoped
public class LoginView implements Serializable {

    private String username;
    private String password;
    private User loginUser;
    private Integer menuType;
    
    @EJB
    private UserAccountService userBean;

    public String login() {
        loginUser = userBean.authenticate(username, password);
        if (loginUser != null) {
            Set<Role> roles = loginUser.getRoles();
            for (Role role : roles) {
                menuType = role.getId();
                if (role.getId() == 1) {
                    return "admin/home?faces-redirect=true";
                } else if (role.getId() == 2) {
                    return "lecturer/home?faces-redirect=true";
                } else
                    return "student/home?faces-redirect=true";
            }
        }
        return null;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }
    
}
