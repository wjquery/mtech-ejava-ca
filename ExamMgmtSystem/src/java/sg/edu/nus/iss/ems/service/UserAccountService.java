package sg.edu.nus.iss.ems.service;


import javax.ejb.Local;
import sg.edu.nus.iss.ems.entity.User;

@Local
public interface UserAccountService {
    public User authenticate(String username, String password);
    public boolean changePwd(String username, String oldPwd, String newPwd);
    public String resetPwd(String username);
}
