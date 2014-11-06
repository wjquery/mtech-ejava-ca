package sg.edu.nus.iss.ems.controller;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sg.edu.nus.iss.ems.entity.User;
import sg.edu.nus.iss.ems.service.UserBean;

@ViewScoped
@Named
public class UserMgmtView implements Serializable {
    
    @EJB
    private UserBean userBean;
            
    private User user;
    
    public List<User> users() {
        System.out.println("==>" + userBean.findAll().size());
        return userBean.findAll();
    }
    
    
    
}
