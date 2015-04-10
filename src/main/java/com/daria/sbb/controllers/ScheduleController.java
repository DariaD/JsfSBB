package com.daria.sbb.controllers;

import com.daria.sbb.ejb.StopStationEJB;
import com.daria.sbb.jpa.entities.Station;
import com.daria.sbb.jpa.entities.StopStation;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Дарья on 10.04.2015.
 */

@ManagedBean
@SessionScoped
public class ScheduleController  implements Serializable {

    private static final Logger log = Logger.getLogger(ScheduleController.class.getName());

    private Station selectedStation;
    private Date date;
    private Map<Date, Station> arrive = new TreeMap<Date, Station>();
    private Map<Date, Station> departure = new TreeMap<Date, Station>();

    @EJB
    StopStationEJB stopStationEJB = new StopStationEJB();

    public String searchTrains(){
        List<StopStation> stopList = stopStationEJB.getByDateAndName(selectedStation.getName(), date);
        for (StopStation stopStation : stopList) {
            Station stationFrom = stopStation.getTrainDeparture().getStationFrom();
            Station stationTo = stopStation.getTrainDeparture().getStationTo();
            if(stationFrom.equals(selectedStation)){
                departure.put(stopStation.getDate(), stationTo);
            } else if (stationTo.equals(selectedStation)){
                arrive.put(stopStation.getDate(), stationFrom);
            } else {
                departure.put(stopStation.getDate(), stationTo);
                arrive.put(stopStation.getDate(), stationFrom);
            }
        }
        return "success";
    }

    public Station getSelectedStation() {
        return selectedStation;
    }

    public void setSelectedStation(Station selectedStation) {
        this.selectedStation = selectedStation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<Date, Station> getArrive() {
        return arrive;
    }

    public void setArrive(Map<Date, Station> arrive) {
        this.arrive = arrive;
    }

    public Map<Date, Station> getDeparture() {
        return departure;
    }

    public void setDeparture(Map<Date, Station> departure) {
        this.departure = departure;
    }
}
