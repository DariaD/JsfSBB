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
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Date;

@FacesValidator("NumberValidator")
public class NumberValidator implements Validator{

    private static final Logger log = Logger.getLogger(DateValidator.class.getName());
    private int places;

    public NumberValidator(){}

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        places = Integer.parseInt(value.toString());
        log.info("Input number of places: " + places);
        if(places <= 0){
            log.info("Validation failed!");
            FacesMessage msg = new FacesMessage("Number validation failed.", "Numbers should be positive.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        } else {
            log.info("Validation ok!");
        }

    }
}
