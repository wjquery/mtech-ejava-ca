package sg.edu.nus.iss.ems.service.impl;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import sg.edu.nus.iss.ems.entity.Role;
import sg.edu.nus.iss.ems.entity.User;
import sg.edu.nus.iss.ems.service.UserAccountService;
import sg.edu.nus.iss.ems.service.UserMgmtService;
import sg.edu.nus.iss.ems.util.EncryptionUtils;

@Stateless
public class UserBean extends GenericDataAccessService<User> implements UserAccountService, UserMgmtService {
    
    private static final String AUTH = 
            "select u from User u where u.username = :username and u.password = :password and u.status != 0";
    private static final String FIND_ALL = "User.findAll";
    private static final String FIND_ALL_ROLES = "Role.findAll";
    private static final String FIND_BY_USERNAME = "User.findByUsername";
    
    @Override
    public List<User> findAll() {
        return em.createNamedQuery(FIND_ALL, User.class).getResultList();
    }
    
    @Override
    public void delete(User user) {
        user.setStatus(0);
        super.update(user);
    }
    
    @Override
    public List<Role> findAllRoles() {
        return em.createNamedQuery(FIND_ALL_ROLES, Role.class).getResultList();
    }
    
    @Override
    public Role loadRole(Serializable primaryKey) {
        return em.find(Role.class, primaryKey);
    }
    
    @Override
    public User authenticate(String username, String password) {
        String encryptedPwd = EncryptionUtils.encryt(password, username);
        TypedQuery<User> q = em.createQuery(AUTH, User.class)
                .setParameter("username", username)
                .setParameter("password", encryptedPwd);
        return unique(q);
    }
    
    @Override
    public boolean changePwd(String username, String oldPwd, String newPwd) {
        User user = authenticate(username, oldPwd);
        if (user != null) {
            user.setPassword(EncryptionUtils.encryt(newPwd));
            return true;
        }
        return false;
    }
    
    @Override
    public String resetPwd(String username) {
        TypedQuery<User> q = em.createNamedQuery(FIND_BY_USERNAME, User.class)
                .setParameter("username", username);
        User user = unique(q);
        if (user != null) {
            String newPwd = "";
            user.setPassword(EncryptionUtils.encryt(newPwd));
            return newPwd;
        }
        return null;
    }
    
     @Override
    public boolean isUserExisted(String username) {
        TypedQuery<User> q = em.createNamedQuery(FIND_BY_USERNAME, User.class)
                .setParameter("username", username);
        List<User> result = q.getResultList();
        return result.isEmpty();
    }
    
}
