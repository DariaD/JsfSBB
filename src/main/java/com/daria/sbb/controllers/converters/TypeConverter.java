package com.daria.sbb.controllers.converters;

import com.daria.sbb.jpa.entities.TrainType;
import com.daria.sbb.jpa.primfaceServices.TypeService;
import org.apache.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Created by Дарья on 07.04.2015.
 */

@FacesConverter("typeConverter")
public class TypeConverter implements Converter {


    private static final Logger log = Logger.getLogger(StationConverter.class.getName());

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                TypeService service = (TypeService) fc.getExternalContext().getApplicationMap().get("typeService");
                TrainType trainTypeByID = service.getTrainTypeByID(Integer.parseInt(value));
                log.info(String.format("TrainType converter return this: %s", trainTypeByID));
                return trainTypeByID;
            } catch(NumberFormatException e) {
                log.info("TrainType converter error!!! NumberFormatException");
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid station."));
            }
        }
        else {
            log.info("TrainType converter return null");
            return null;
        }
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((TrainType) object).getIdTrainType());
        }
        else {
            return null;
        }
    }
}
