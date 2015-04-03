package com.daria.sbb.cdi;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Created by Дарья on 01.04.2015.
 */
public class SessionUtil {

    public  HttpSession getSession(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        return session;

    }
}
