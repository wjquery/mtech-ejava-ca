package sg.edu.nus.iss.ems.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sg.edu.nus.iss.ems.entity.User;

@Deprecated
@Stateless
public class UserFacade extends AbstractFacade<User> {
    
    @PersistenceContext(unitName = "ExamMgmtSystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    // remove, findAll, findRange, count methods are overriden for User, Module Facade
    @Override
    public void remove(User user) {
        user.setStatus(0);
        update(user);
    }
    
    @Override
    public List<User> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<User> rt = cq.from(User.class);
        cq.where(cb.notEqual(rt.get("status"), 0));
        cq.select(rt);
        return em.createQuery(cq).getResultList();
    }
    
    @Override
    public List<User> findRange(int[] range) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<User> rt = cq.from(User.class);
        cq.where(cb.notEqual(rt.get("status"), 0));
        cq.select(rt);
        Query q = em.createQuery(cq)
                .setMaxResults(range[1] - range[0] + 1)
                .setFirstResult(range[0]);
        return q.getResultList();
    }
    
    @Override
    public int count() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<User> rt = cq.from(User.class);
        cq.where(cb.notEqual(rt.get("status"), 0));
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
