package sg.edu.nus.iss.ems.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import sg.edu.nus.iss.ems.entity.User;

@Stateless
public class UserBean extends GenericDataAccessService<User> {
    
    private static final String AUTH = 
            "select u from User u where u.username = :username and u.password = :password and u.status != 0";
    private static final String FIND_ALL = "User.findAll";
    
    public List<User> findAll() {
        return em.createNamedQuery(FIND_ALL, User.class).getResultList();
    }
    
    public void delete(User user) {
        user.setStatus(0);
        super.update(user);
    }
    
    public User authenticate(String username, String password) {
        TypedQuery<User> q = em.createQuery(AUTH, User.class);
        q.setParameter("username", username);
        q.setParameter("password", password);
        
        User user = null;
        try {
            user = q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public boolean changePwd(String username, String oldPwd, String newPwd) {
        User user = authenticate(username, oldPwd);
        if (user != null) {
            user.setPassword(newPwd);
        }
        return true;
    }
}
