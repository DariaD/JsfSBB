package com.daria.utils.validators;

import com.daria.sbb.ejb.StationEJB;
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

/**
 * Created by Дарья on 07.04.2015.
 */

@ManagedBean
@SessionScoped
@FacesValidator("StationValidator")
public class StationValidation implements Validator {

    private static final Logger log = Logger.getLogger(StationValidation.class.getName());

    @EJB
    private StationEJB stationEJB = new StationEJB();

    private String stationName;

    public StationValidation(){}

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        stationName = value.toString();
        log.info("Input station name: " + stationName);
        if(stationEJB.isExist(stationName) || stationName.trim().length()==0){
            log.info("Validation failed!");
            FacesMessage msg = new FacesMessage("Such station already exist", "Please choose another station name.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        } else {
            log.info("Validation ok!");
        }

    }
}
