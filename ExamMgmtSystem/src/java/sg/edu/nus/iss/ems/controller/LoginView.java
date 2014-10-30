/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ems.controller;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import sg.edu.nus.iss.ems.entity.User;
import sg.edu.nus.iss.ems.viewmodel.Credentials;

/**
 *
 * @author Wenyan
 */
@Named(value = "loginView")
@SessionScoped
public class LoginView implements Serializable {

    Credentials credentials;
    private User user;

    public void login() {
        
    }

    public void logout() {
        user = null;
    }


    public boolean isLoggedIn() {
       return user!=null;
    }

    public LoginView() {
    }
    
}
