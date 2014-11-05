package sg.edu.nus.iss.ems.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sg.edu.nus.iss.ems.entity.User;

@Stateless
public class UserBean {
    
    private static final String authenticate = 
            "select u from User u where u.username = :username and u.password = :password";
    
    @PersistenceContext
    private EntityManager em;
    
    public User login(String username, String password) {
        TypedQuery<User> q = em.createQuery(authenticate, User.class);
        q.setParameter("username", username);
        q.setParameter("password", password);
        return q.getSingleResult();
    }
}
