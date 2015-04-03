package com.daria.sbb.controllers;

import com.daria.sbb.ejb.UserEJB;
import com.daria.sbb.jpa.entities.User;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Date;

/**
 * Created by Дарья on 01.04.2015.
 */

@ManagedBean
@SessionScoped
public class RegistrationController {
    @EJB
    private UserEJB userEJB = new UserEJB();

    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private Date date;

    private String errorMessage;

    public String addUser(){
        User user = new User();
        if(userEJB.isExist(login)){
            errorMessage = "Such user already exist. Please enter another name.";
            return "error/loginError.xhtml";

        }
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setDateOfBirth(date);
        userEJB.addNew(user);
        return "successRegistrationPage.xhtml";
    }

    public String loginRedirect(){

        return "/index";
    }

    public String registrationRedirect(){
        return "common/registration.xhtml";
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login.toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
