package com.daria.sbb.controllers.admin;

import com.daria.sbb.ejb.DepartureEJB;
import com.daria.sbb.ejb.TicketEJB;
import com.daria.sbb.jpa.entities.Ticket;
import com.daria.sbb.jpa.entities.TrainDeparture;
import com.daria.sbb.jpa.stuff.TrainDepartureRecord;
import com.daria.sbb.jpa.stuff.UserRecord;
import com.daria.utils.DateTime;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дарья on 12.04.2015.
 */
@ManagedBean
@SessionScoped
public class TrainPassengerController implements Serializable {

    private List<TrainDepartureRecord> listToShowTrains = new ArrayList<TrainDepartureRecord>();
    private List<UserRecord> listToShowPassenger = new ArrayList<UserRecord>();

    DateTime dt = new DateTime();

    @EJB
    private DepartureEJB departureEJB;
    @EJB
    private TicketEJB ticketEJB;

    public List<TrainDepartureRecord> getListToShowTrains() {
        listToShowTrains.clear();
        List<TrainDeparture> trainDepartureList = departureEJB.getAll();
        for(TrainDeparture daparture : trainDepartureList){
            listToShowTrains.add(new TrainDepartureRecord(daparture.getTrain().getName(),
                    dt.getDateTimeAsString(daparture.getDepartureTime()), daparture.getTrain().getTrainType().getType(),
                    daparture.getStationFrom().getName(), daparture.getStationTo().getName()));
        }
        return listToShowTrains;
    }

    public void setListToShowTrains(List<TrainDepartureRecord> listToShowTrains) {
        this.listToShowTrains = listToShowTrains;
    }

    public List<UserRecord> getListToShowPassenger() {
        listToShowPassenger.clear();
        List<Ticket> ticketList = ticketEJB.getAll();
        for (Ticket ticket : ticketList) {
            listToShowPassenger.add(new UserRecord( String.format("%s: %s - %s",
                    ticket.getTrainDeparture().getTrain().getName(), ticket.getTrainDeparture().getStationFrom().getName(), ticket.getTrainDeparture().getStationTo().getName()),
                    dt.getDateTimeAsString(ticket.getTrainDeparture().getDepartureTime()),
                    String.format("%s %s", ticket.getUser().getFirstName(), ticket.getUser().getSecondName()), dt.getDateAsString(ticket.getUser().getDateOfBirth())));
        }

        return listToShowPassenger;
    }

    public void setListToShowPassenger(List<UserRecord> listToShowPassenger) {
        this.listToShowPassenger = listToShowPassenger;
    }


}
