package sg.edu.nus.iss.ems.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import sg.edu.nus.iss.ems.entity.User;
import sg.edu.nus.iss.ems.util.EncryptionUtils;

@Stateless
public class UserBean extends GenericDataAccessService<User> {
    
    private static final Logger LOGGER = Logger.getLogger(GenericDataAccessService.class.getName()); 
    private static final String AUTH = 
            "select u from User u where u.username = :username and u.password = :password and u.status != 0";
    private static final String FIND_ALL = "User.findAll";
    
    @Override
    public List<User> findAll() {
        return em.createNamedQuery(FIND_ALL, User.class).getResultList();
    }
    
    @Override
    public void delete(User user) {
        user.setStatus(0);
        super.update(user);
    }
    
    public User authenticate(String username, String password) {
        String encryptedPwd = EncryptionUtils.encryt(password, username);
        TypedQuery<User> q = em.createQuery(AUTH, User.class)
                .setParameter("username", username)
                .setParameter("password", encryptedPwd);
        
        User user = null;
        try {
            user = q.getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return user;
    }
    
    public boolean changePwd(String username, String oldPwd, String newPwd) {
        User user = authenticate(username, oldPwd);
        if (user != null) {
            user.setPassword(EncryptionUtils.encryt(newPwd));
        }
        return true;
    }
}
