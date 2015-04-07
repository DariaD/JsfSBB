package com.daria.utils.validators;

/**
 * Created by Дарья on 07.04.2015.
 */

import com.daria.sbb.ejb.UserEJB;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean
@SessionScoped
@FacesValidator("LoginValidator")
public class LoginValidation implements Validator{

    private static final Logger log = Logger.getLogger(LoginValidation.class.getName());

    @EJB
    private UserEJB userEJB = new UserEJB();

    private String login;

    public LoginValidation(){}

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        login = value.toString();
        log.info("Input login: " + login);
        if(userEJB.isExist(login)){
            log.info("Validation failed!");
            FacesMessage msg = new FacesMessage("Such user already exist", "Please choose another login name.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        } else {
            log.info("Validation ok!");
        }

    }
}

