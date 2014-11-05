package sg.edu.nus.iss.ems.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sg.edu.nus.iss.ems.entity.Module;

@Stateless
public class ModuleBean {
    
    @PersistenceContext
    private EntityManager em;
    
    public Module findModuleByCode(String code) {
        return em.find(Module.class, code);
    }
    
    public List<Module> findAllModules() {
        return em.createNamedQuery("Module.findAll", Module.class).getResultList();
    }
}
