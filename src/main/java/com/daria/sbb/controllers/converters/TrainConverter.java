package com.daria.sbb.controllers.converters;

/**
 * Created by Дарья on 10.04.2015.
 */

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.daria.sbb.jpa.entities.Train;
import com.daria.sbb.jpa.primfaceServices.TrainService;
import org.apache.log4j.Logger;


@FacesConverter("trainConverter")
public class TrainConverter implements Converter {

    private static final Logger log = Logger.getLogger(TrainConverter.class.getName());

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                TrainService service = (TrainService) fc.getExternalContext().getApplicationMap().get("trainService");
                Train trainByID = service.getTrainByID(Integer.parseInt(value));
                log.info(String.format("Train converter return this: %s", trainByID));
                return trainByID;
            } catch(NumberFormatException e) {
                log.info("Train converter error!!! NumberFormatException");
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid train."));
            }
        }
        else {
            log.info("Station converter return null");
            return null;
        }
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Train) object).getId());
        }
        else {
            return null;
        }
    }
}

