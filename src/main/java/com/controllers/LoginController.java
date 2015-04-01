package com.controllers;

import com.ejb.UserEJB;
import com.jpa.entities.User;
import com.logic.Util;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Дарья on 31.03.2015.
 */

@Named(value = "loginController")
@SessionScoped
@ManagedBean
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private UserEJB userEJB;
    private User user = new User();
    private String login;
    private String password;

    private String message;

    public String validateUser(){
        boolean result = userEJB.login(login, password);
        if (result) {
            // get Http Session and store username
            HttpSession session = Util.getSession();
            List<User> usersByLogin = userEJB.findUsersByLogin(login);
            session.setAttribute("user", usersByLogin);
            session.setAttribute("login", login);
            if(login.equals("admin") || login.equals("1")) {
                return "helloAdmin";
            } else {
                return "welcome";
            }
        } else {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
//                            "Invalid Login!", "Please Try Again!"));
            return "hello";
        }
////
    }

    public String logout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        return "login";
    }

    public UserEJB getUserEJB() {
        return userEJB;
    }
    public void setUserEJB(UserEJB testEJB) {
        this.userEJB = testEJB;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String m) {
        this.message = String.format("%s\n %s", message, m);
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}