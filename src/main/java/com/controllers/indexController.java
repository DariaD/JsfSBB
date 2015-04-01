package com.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 * Created by Дарья on 01.04.2015.
 */

@Named(value = "indexController")
@SessionScoped
@ManagedBean
public class indexController {
    private String login;
    private String password;

    public String validateUser(){
        return "welcome";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
