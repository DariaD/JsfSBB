package com.controllers;

/**
 * Created by Дарья on 01.04.2015.
 */

import com.ejb.UserEJB;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class BasicView {

    private String text;
    private String password;
    @EJB
    private UserEJB userEJB = new UserEJB();

    public String login(){
        boolean isLogin = userEJB.login(text, password);
        if(isLogin){
          if(text.equals("admin") || text.equals("admin")){
              return "welcomAdmin";
          } else {
              return "welcomUser";
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
