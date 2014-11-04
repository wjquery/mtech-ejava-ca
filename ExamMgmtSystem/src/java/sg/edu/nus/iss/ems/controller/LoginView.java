/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ems.controller;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "loginView")
@RequestScoped
public class LoginView implements Serializable {

    private String username;
    private String password;

    public String login() {
        System.out.println(username + ":" + password);
        if ("admin".equals(username) && "password".equals(password))
            return "lecturer/home?faces-redirect=true";
        else if ("student".equals(username) && "password".equals(password))
            return "student/home?faces-redirect=true";
        else
            return null;
    }

    public void logout() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
