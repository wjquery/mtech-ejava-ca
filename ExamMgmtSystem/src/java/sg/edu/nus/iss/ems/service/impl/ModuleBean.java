package sg.edu.nus.iss.ems.service.impl;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import sg.edu.nus.iss.ems.entity.Module;
import sg.edu.nus.iss.ems.entity.User;
import sg.edu.nus.iss.ems.service.ModuleMgmtService;

@Stateless
public class ModuleBean extends GenericDataAccessService<Module> implements ModuleMgmtService {
    
    private static final String FIND_ALL = "Module.findAll";
    
    @Override
    public List<Module> findAll() {
        return em.createNamedQuery(FIND_ALL, Module.class).getResultList();
    }
    
    @Override
    public void delete(Module module) {
        module.setStatus(0);
        super.update(module);
    }
    
    @Override
    public boolean isModuleExisted(String moduleCode){
        TypedQuery<Module> q = em.createNamedQuery("Module.findByCode", Module.class)
                .setParameter("code", moduleCode);
        List<Module> result = q.getResultList();
        return result.isEmpty();
    }
    
}
