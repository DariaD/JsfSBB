package com.daria.sbb.controllers;

import com.daria.sbb.ejb.UserEJB;
import com.daria.sbb.jpa.entities.User;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Дарья on 01.04.2015.
 */

@ManagedBean
@SessionScoped
public class RegistrationController implements Serializable {
    @EJB
    private UserEJB userEJB = new UserEJB();

    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private Date date;

    private String messages;

    public String addUser(){
        User user = new User();
//        if(userEJB.isExist(login)){
//            addErrorMessage("Such user already exist. Please enter another name.");
//            return "pages/error/loginError.xhtml";
//
//        }
//        if(login != null &&  password != null && firstName != null && secondName != null && date != null) {
            user.setLogin(login);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setSecondName(secondName);
            user.setDateOfBirth(date);
            userEJB.addNew(user);
            return "successRegistrationPage.xhtml";
//        } else {
//            addErrorMessage("Such user already exist. Please enter another login.");
//            return "error/loginError.xhtml";
//        }
    }

    public String loginRedirect(){
        return "index";
    }

    public String registrationRedirect(){
        return "/pages/common/registration.xhtml";
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
        Date currentDate = new Date();
        if(currentDate.before(date)){
            this.date = null;
            addErrorMessage("You can't select date for birthday from future. Please enter correct date");
        } else {
            this.date = date;
        }
    }

    public String getErrorMessage() {
        return messages;
    }

    public void setErrorMessage(String errorMessage) {
        this.messages = errorMessage;
    }

    private  void addErrorMessage(String newError){
        messages = String.format("%s /n %s", messages, newError);

    }
}
