package sg.edu.nus.iss.ems.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sg.edu.nus.iss.ems.entity.Module;
import sg.edu.nus.iss.ems.service.ModuleBean;

@ViewScoped
@Named
public class ModuleView implements Serializable {
    
    @EJB ModuleBean moduleBean;
    
    private String module;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
    
    public List<Module> modules() {
        return moduleBean.findAll();
    }
    
    public Map<String, String> moduleList() {
        List<Module> modules = moduleBean.findAll();
        Map<String, String> moduleList = new HashMap<String, String>();
        for (Module module : modules) {
            moduleList.put(module.getCode() + " " +  module.getName(), module.getCode());
        }
        return moduleList;
    }
}
