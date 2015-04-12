package com.daria.sbb.controllers.converters;

/**
 * Created by Дарья on 11.04.2015.
 */


        import javax.faces.application.FacesMessage;
        import javax.faces.component.UIComponent;
        import javax.faces.context.FacesContext;
        import javax.faces.convert.Converter;
        import javax.faces.convert.ConverterException;
        import javax.faces.convert.FacesConverter;

        import com.daria.sbb.jpa.entities.Station;
        import com.daria.sbb.jpa.entities.TrainDeparture;
        import com.daria.sbb.jpa.primfaceServices.StationService;
        import com.daria.sbb.jpa.primfaceServices.TrainDepartureService;
        import org.apache.log4j.Logger;


@FacesConverter("trainDepartureConverter")
public class TrainDepartureConverter implements Converter {

    private static final Logger log = Logger.getLogger(TrainDepartureConverter.class.getName());

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                TrainDepartureService service = (TrainDepartureService) fc.getExternalContext().getApplicationMap().get("trainDepartureService");
                TrainDeparture stationByID = service.getTrainDepartureByID(Integer.parseInt(value));
                log.info(String.format("TrainDepartureService converter return this: %s", stationByID));
                return stationByID;
            } catch(NumberFormatException e) {
                log.info("Station converter error!!! NumberFormatException");
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Departure."));
            }
        }
        else {
            log.info("Station converter return null");
            return null;
        }
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((TrainDeparture) object).getIdTrainDeparture());
        }
        else {
            return null;
        }
    }
}
