package sg.edu.nus.iss.ems.controller;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sg.edu.nus.iss.ems.entity.Module;
import sg.edu.nus.iss.ems.service.ModuleBean;

@ViewScoped
@Named
public class ModuleView implements Serializable {
    
    @EJB ModuleBean moduleBean;
    
    public List<Module> modules() {
        return moduleBean.findAllModules();
    }
}
