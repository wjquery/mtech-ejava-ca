package sg.edu.nus.iss.ems.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sg.edu.nus.iss.ems.entity.Module;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class QuestionSeqGenerator {
    
    private Map<String, Integer> moduleQnSeq;
    
    @PersistenceContext
    private EntityManager em;
    
    @PostConstruct
    private void init() {
        moduleQnSeq = new HashMap<String, Integer>();
        List<Module> modules = em.createNamedQuery("Module.findAll", Module.class).getResultList();
        for (Module module : modules) {
            moduleQnSeq.put(module.getCode(), module.getQuestionCount());
        }
    }
    
    @Lock(LockType.READ)
    @AccessTimeout(value = 5, unit = TimeUnit.SECONDS)
    public Integer current(String moduleCode) {
        return moduleQnSeq.get(moduleCode);
    }
    
    @Lock(LockType.WRITE)
    @AccessTimeout(value = 5, unit = TimeUnit.SECONDS)
    public Integer next(String moduleCode) {
        Integer next = moduleQnSeq.get(moduleCode) + 1;
        moduleQnSeq.put(moduleCode, next);
        
        Module module = em.createNamedQuery("Module.findByCode", Module.class)
                .setParameter("code", moduleCode)
                .getSingleResult();
        module.setQuestionCount(next);
        em.merge(module);
        
        return next;
    }
}
