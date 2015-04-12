package com.daria.sbb.controllers;

/**
 * Created by Дарья on 01.04.2015.
 */

import com.daria.sbb.ejb.UserEJB;
import com.daria.sbb.jpa.entities.User;
import com.daria.sbb.cdi.SessionUtil;
import org.jboss.arquillian.core.api.annotation.Inject;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class BasicView implements Serializable {

    private String text;
    private String password;
    @EJB
    private UserEJB userEJB = new UserEJB();

    @Inject
    SessionUtil sessionUtil = new SessionUtil();

    public String login(){
        HttpSession session = sessionUtil.getSession();
        User userFromBase = userEJB.login(text, password);
        if(userFromBase != null){
              session.setAttribute("user", userFromBase);
          if(text.toLowerCase().equals("admin") || text.equals("1")){
              session.setAttribute("role", "admin");
              return "/pages/admin/welcomeAdmin.xhtml";
          } else {
              session.setAttribute("role", "user");
              return "/pages/user/welcomUser.xhtml";
          }
        }
        return "nosuchuser";
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
