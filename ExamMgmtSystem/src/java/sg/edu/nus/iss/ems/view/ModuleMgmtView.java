package sg.edu.nus.iss.ems.view;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sg.edu.nus.iss.ems.entity.Module;
import sg.edu.nus.iss.ems.service.ModuleMgmtService;
import sg.edu.nus.iss.ems.util.JsfUtil;

@ViewScoped
@Named
public class ModuleMgmtView implements Serializable {
    
    @EJB ModuleMgmtService moduleBean;
    
    private Module selectedModule;
    private List<Module> modules;
    
    // setters & getters
    public Module getSelectedModule() {
        return selectedModule;
    }

    public void setSelectedModule(Module selectedModule) {
        this.selectedModule = selectedModule;
    }
    
    public List<Module> getModules() {
        if (modules == null)
            modules = moduleBean.findAll();
        return modules;
    }
    
    // CRUD methods
    public Module prepareCreate() {
        selectedModule = new Module();
        return selectedModule;
    }
    
    public void create() {
        moduleBean.create(selectedModule);
        if (!JsfUtil.isValidationFailed()) {
            modules = null;    // Invalidate list of modules to trigger re-query.
        }
    }
    
    public void detele() {
        moduleBean.delete(selectedModule);
        if (!JsfUtil.isValidationFailed()) {
            selectedModule = null; // Remove selection
            modules = null;    // Invalidate list of modules to trigger re-query.
        }
    }
    
}
