package sg.edu.nus.iss.ems.service;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import sg.edu.nus.iss.ems.entity.Module;

@Local
public interface ModuleMgmtService {
    public List<Module> findAll();
    public Module load(Serializable primaryKey);
    public void create(Module module);
    public Module update(Module module);
    public void delete(Module module);
}
