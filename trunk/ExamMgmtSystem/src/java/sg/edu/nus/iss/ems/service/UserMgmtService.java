package sg.edu.nus.iss.ems.service;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import sg.edu.nus.iss.ems.entity.User;

@Local
public interface UserMgmtService {
    public void create(User user);
    public User update(User user);
    public void delete(User user);
    public User load(Serializable primaryKey);
    public List<User> findAll();
    
    
}
