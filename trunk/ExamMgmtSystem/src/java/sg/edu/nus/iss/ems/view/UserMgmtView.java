package sg.edu.nus.iss.ems.view;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sg.edu.nus.iss.ems.entity.User;
import sg.edu.nus.iss.ems.service.UserMgmtService;

@ViewScoped
@Named
public class UserMgmtView implements Serializable {
    
    @EJB
    private UserMgmtService userBean;
    
    private User user;
    
    public List<User> users() {
        return userBean.findAll();
    }
    
    
    
}
