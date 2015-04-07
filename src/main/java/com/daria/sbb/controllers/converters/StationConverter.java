package com.daria.sbb.controllers.converters;

/**
 * Created by Дарья on 07.04.2015.
 */


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.daria.sbb.jpa.entities.Station;
import com.daria.sbb.jpa.primfaceServices.StationService;
import org.apache.log4j.Logger;


@FacesConverter("stationConverter")
public class StationConverter implements Converter {

    private static final Logger log = Logger.getLogger(StationConverter.class.getName());

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                StationService service = (StationService) fc.getExternalContext().getApplicationMap().get("stationService");
                Station stationByID = service.getStationByID(Integer.parseInt(value));
                log.info(String.format("Station converter return this: ", stationByID));
                return stationByID;
            } catch(NumberFormatException e) {
                log.info(String.format("Station converter error!!! NumberFormatException"));
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid station."));
            }
        }
        else {
            log.info(String.format("Station converter return null"));
            return null;
        }
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Station) object).getId());
        }
        else {
            return null;
        }
    }
}