package com.daria.utils.validators;

/**
 * Created by Дарья on 12.04.2015.
 */

import com.daria.sbb.cdi.SessionUtil;
import com.daria.sbb.ejb.StopStationEJB;
import com.daria.sbb.ejb.TicketEJB;
import com.daria.sbb.jpa.entities.StopStation;
import com.daria.sbb.jpa.entities.TrainDeparture;
import com.daria.sbb.jpa.entities.User;
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
import javax.servlet.http.HttpSession;
import java.util.List;

@ManagedBean
@SessionScoped
@FacesValidator("TicketValidator")
public class TicketValidator implements Validator {


    private static final Logger log = Logger.getLogger(TicketValidator.class.getName());

    @EJB
    private TicketEJB ticketEJB = new TicketEJB();
    @EJB
    private StopStationEJB stopStationEJB = new StopStationEJB();

    private TrainDeparture selectedTrain;

    public TicketValidator(){}

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        selectedTrain = (TrainDeparture) value;
        SessionUtil sessionUtil = new SessionUtil();
        HttpSession session = sessionUtil.getSession();
        User user = (User) session.getAttribute("user");
        boolean fail = false;
        if(selectedTrain == null) {
            FacesMessage msg = new FacesMessage("Train is not selected", "Please select the train");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            log.info("Selected Train is null!!!");
            throw new ValidatorException(msg);
        }
        log.info("Selected Train: " + selectedTrain);
        int availablePlaces = getMinNumberOfPlace(selectedTrain);
        if(availablePlaces == 0) {
            FacesMessage msg = new FacesMessage("There is no free places any more", "Please select another train");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            log.info("No free places!!!");
            throw new ValidatorException(msg);
        }
        if(ticketEJB.isExist(user, selectedTrain)){
            FacesMessage msg = new FacesMessage("You already registered for this train", "You can't do it twice. Please select another train.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            log.info("This user already registered for this train");
            throw new ValidatorException(msg);
        }
            log.info("Validation ok!");
    }

    private int getMinNumberOfPlace(TrainDeparture trainDeparture) {
        List<StopStation> listStationFrom = stopStationEJB.getByDepartureID(trainDeparture);
        int res = listStationFrom.get(0).getCurrPlaceAvalable();
        for (StopStation stopStation : listStationFrom) {
            if(!stopStation.getStation().equals(trainDeparture.getStationTo()) && res < stopStation.getCurrPlaceAvalable()){
                res = stopStation.getCurrPlaceAvalable();
            }
        }
        return res;
    }
}
