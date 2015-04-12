package com.daria.sbb.controllers;

import com.daria.sbb.cdi.SessionUtil;
import org.apache.log4j.Logger;
import org.jboss.arquillian.core.api.annotation.Inject;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;

/**
 * Created by Дарья on 01.04.2015.
 */

@ManagedBean
public class MenuNavigation {
    private String selectedMenu;

    @Inject
    SessionUtil sessionUtil = new SessionUtil();

    private static final Logger log = Logger.getLogger(MenuNavigation.class.getName());


    public String logout(){
        log.info("Try to logout!!!");
        HttpSession session = sessionUtil.getSession();
        session.setAttribute("user", null);
        session.setAttribute("role", null);
        session.invalidate();

        return "index";
    }

    public boolean isLogin() {
        HttpSession session = sessionUtil.getSession();
        Object user = session.getAttribute("user");
        if( user != null ){
            return true;
        }
        return false;
    }

    private String selectMenu(){
        HttpSession session = sessionUtil.getSession();
        if(session == null){
            return "/pages/common/commonMenu.xhtml";
        }
        Object user = session.getAttribute("user");
        if( user != null ){
            if( session.getAttribute("role").equals("admin")){
                return "/pages/admin/adminMenu.xhtml";
            }
            return "/pages/user/userMenu.xhtml";
        }
        return "/pages/common/commonMenu.xhtml";
    }

    public String getSelectedMenu(){
        selectedMenu = selectMenu();
        return selectedMenu;
    }

    public void setSelectedMenu(String selectedMenu) {
        this.selectedMenu = selectedMenu;
    }
}
