package sg.edu.nus.iss.ems.view;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sg.edu.nus.iss.ems.entity.Module;
import sg.edu.nus.iss.ems.service.ModuleMgmtService;

@ViewScoped
@Named
public class ModuleMgmtView implements Serializable {
    
    @EJB ModuleMgmtService moduleBean;
    
    private String selectedModule;
    private List<Module> modules;
    
    public String getSelectedModule() {
        return selectedModule;
    }

    public void setSelectedModule(String selectedModule) {
        this.selectedModule = selectedModule;
    }
    
    public List<Module> getModules() {
        return modules;
    }
    
    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
    
}
