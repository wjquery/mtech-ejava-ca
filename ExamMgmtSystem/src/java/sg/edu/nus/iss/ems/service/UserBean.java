package sg.edu.nus.iss.ems.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sg.edu.nus.iss.ems.entity.User;

@Stateless
public class UserBean {
    
    private static final String AUTH = 
            "select u from User u where u.username = :username and u.password = :password and u.status != 0";
    private static final String FIND_ALL = "User.findAll";
    
    @PersistenceContext
    private EntityManager em;
    
    public User login(String username, String password) {
        TypedQuery<User> q = em.createQuery(AUTH, User.class);
        q.setParameter("username", username);
        q.setParameter("password", password);
        return q.getSingleResult();
    }
    
    public List<User> findAll() {
        return em.createNamedQuery(FIND_ALL, User.class).getResultList();
    }
    
    public void create(User user) {
        em.persist(user);
        em.flush();
        em.refresh(user);
    }
    
    public User update(User user) {
        return em.merge(user);
    }
    
    public User load(int id) {
        return em.find(User.class, id);
    }
    
    public User delete(User user) {
        user.setStatus(0);
        return em.merge(user);
    }
}
