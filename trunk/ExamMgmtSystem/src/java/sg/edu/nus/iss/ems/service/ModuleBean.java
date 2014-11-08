package sg.edu.nus.iss.ems.service;

import java.util.List;
import javax.ejb.Stateless;
import sg.edu.nus.iss.ems.entity.Module;

@Stateless
public class ModuleBean extends GenericDataAccessService<Module>{
    
    private static final String FIND_ALL = "Module.findAll";
    
    public List<Module> findAll() {
        return em.createNamedQuery(FIND_ALL, Module.class).getResultList();
    }
    
    public void delete(Module module) {
        module.setStatus(0);
        super.update(module);
    }
    
    
}