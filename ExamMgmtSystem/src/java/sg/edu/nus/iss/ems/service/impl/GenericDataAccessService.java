package sg.edu.nus.iss.ems.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public abstract class GenericDataAccessService<T> {
    
    private static final Logger LOGGER = Logger.getLogger(GenericDataAccessService.class.getName());
    
    @PersistenceContext
    protected EntityManager em;
    
    private Class<T> clz;
    
    public Class<T> getClz() {
        if (clz == null) {
            ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
            clz = (Class<T>) type.getActualTypeArguments()[0];
        }
        return clz;
    }
    
    public List<T> findAll() {
        return em.createQuery("select e from " + getClz().getSimpleName() + " e", getClz()).getResultList();
    }
    
    public void create(T entity) {
        em.persist(entity);
        em.flush();
        em.refresh(entity);
    }
    
    public T update(T entity) {
        return em.merge(entity);
    }
    
    public T load(Serializable primaryKey) {
        return em.find(getClz(), primaryKey);
    }
    
    public void delete(T entity) {
        em.remove(entity);
    }
    
    protected T unique(TypedQuery<T> query) {
        T entity = null;
        try {
            entity = query.getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return entity;
    }
}
