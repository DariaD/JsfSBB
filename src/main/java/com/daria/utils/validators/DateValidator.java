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

@FacesValidator("DateValidator")
public class DateValidator implements Validator{

    private static final Logger log = Logger.getLogger(DateValidator.class.getName());


    private String inputData;
    private Date date;

    public DateValidator(){}

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        inputData = value.toString();
        log.info("Input date: " + inputData);
        date = (Date) value;

        SimpleDateFormat parserSDF = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
//        try {
            //Date input = parserSDF.parse(inputData);
            Date currentDate = new Date();
            if(currentDate.before(date)){
                log.info("Data can't be from future. Today: " + currentDate);
                FacesMessage msg = new FacesMessage("Data can't be from future.", "Please enter valid data.");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);

            }
//        } catch (ParseException e) {
//            log.info("ParseException: " + e);
//            FacesMessage msg = new FacesMessage("Data validation failed.", "Please enter valid data.");
//            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//            throw new ValidatorException(msg);
//        }

    }
}

