package com.daria.utils;

/**
 * Created by Дарья on 12.04.2015.
 * Filter for psge access.
 */

import org.apache.log4j.Logger;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SecurytiFilter implements Filter{

    private static final Logger log = Logger.getLogger(SecurytiFilter.class.getName());

    public void doFilter(ServletRequest request, ServletResponse response,  FilterChain chain)  throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession();
        if((session!=null) && (session.getAttribute("AccessInfo")!=null)) {
            chain.doFilter(request, response);
        } else {
            //log.info("Path: " + contextPath);
            log.info("Role: " + session.getAttribute("role"));
            if(
    /*Common pages*/
                    req.getRequestURI().equals(contextPath) ||
                    req.getRequestURI().equals(contextPath+"/index.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/faces/index.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/javax.faces.resource/theme.css.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/javax.faces.resource/primefaces.css.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/javax.faces.resource/jquery/jquery.js.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/javax.faces.resource/primefaces.js.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/javax.faces.resource/images/ui-icons_38667f_256x240.png.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/javax.faces.resource/messages/messages.png.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/common/index.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/user/index.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/admin/index.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/common/aboutCompany.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/common/registration.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/common/schedule.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/common/ScheduleResult.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/common/successRegistrationPage.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/error/unknown.xhtml")
    /*Admin pages*/
                   || (session.getAttribute("role") != null && session.getAttribute("role").equals("admin") &&
                            (req.getRequestURI().equals(contextPath+"/faces/pages/admin/welcomeAdmin.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/admin/welcomeAdmin.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/faces/pages/admin/index.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/admin/manage/resultPages/DepartureResult.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/admin/manage/Common.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/admin/manage/Departures.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/admin/manage/Routes.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/admin/manage/managerError.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/admin/manage/Trains.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/admin/TrainsPassengers.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/faces/pages/admin/TrainsPassengers.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/admin/manage/Stations.xhtml"))
                    ) ||
    /*User pages*/
                    (session.getAttribute("role") != null && session.getAttribute("role").equals("user") &&
                            (req.getRequestURI().equals(contextPath+"/pages/user/welcomUser.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/faces/pages/user/index.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/user/buyTicket.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/user/searchTrain.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/user/noSuchTrain.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/user/searchResult.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/user/ConfirmBothTicket.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/user/FailBothTicket.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/user/SuccessBothTicket.xhtml") ||
                    req.getRequestURI().equals(contextPath+"/pages/user/index.xhtml"))
            )
                    ) {
                //if( session.getAttribute("role").equals("admin") && req.getRequestURI().equals(contextPath) || ) {
                    chain.doFilter(request, response);            // страницы доступные без авторизации
                //}
            } else {
                log.info("redirect to index");
                log.info("URL: " + req.getRequestURI());
                if(session.getAttribute("role") != null && session.getAttribute("role").equals("user")){
                    res.sendRedirect(contextPath + "/pages/user/welcomUser.xhtml");
                } else {
                    res.sendRedirect(contextPath + "/index.xhtml");    // для остальных редиректим на логин
                }
            }
        }
    }
    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
    }
}
