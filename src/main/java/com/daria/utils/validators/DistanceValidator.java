package com.daria.utils.validators;

/**
 * Created by Дарья on 07.04.2015.
 */

import org.apache.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("DistanceValidator")
public class DistanceValidator implements Validator{

    private static final Logger log = Logger.getLogger(DistanceValidator.class.getName());


    private int distance;

    public DistanceValidator(){}

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        distance = Integer.parseInt(value.toString());
        log.info("Input distance: " + distance);
        if(distance <= 0){
            log.info("Validation failed!");
            FacesMessage msg = new FacesMessage("Distance validation failed.", "Distance should be positive.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        } else {
            log.info("Validation ok!");
        }

    }
}
