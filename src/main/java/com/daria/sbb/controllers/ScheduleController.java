package com.daria.sbb.controllers;

import com.daria.sbb.ejb.StopStationEJB;
import com.daria.sbb.jpa.entities.Station;
import com.daria.sbb.jpa.entities.StopStation;
import com.daria.sbb.jpa.stuff.ScheduleRecord;
import com.daria.utils.DateTime;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

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

    private List<ScheduleRecord> arriveList = new ArrayList<ScheduleRecord>();

    private List<ScheduleRecord> departureList = new ArrayList<ScheduleRecord>();

    DateTime dt = new DateTime();

    @EJB
    StopStationEJB stopStationEJB = new StopStationEJB();

    public String searchTrains(){
        arriveList.clear();
        departureList.clear();
        List<StopStation> stopList = stopStationEJB.getByStation(selectedStation);
        for (StopStation stopStation : stopList) {
            Date dateStop = stopStation.getDate();
            if(!dt.getDateOnly(dateStop).equals(dt.getDateOnly(date))){
                continue;
            }
            Station stationFrom = stopStation.getTrainDeparture().getStationFrom();
            Station stationTo = stopStation.getTrainDeparture().getStationTo();
            if(stationFrom.equals(selectedStation)){
                departure.put(dateStop, stationTo);
            } else if (stationTo.equals(selectedStation)){
                arrive.put(dateStop, stationFrom);
            } else {
                departure.put(dateStop, stationTo);
                arrive.put(dateStop, stationFrom);
            }
        }
        if(arrive.isEmpty()){
            arriveList.add(new ScheduleRecord("There is no arrived trains", ""));
        } else {
            for (Date date : arrive.keySet()) {
                arriveList.add(new ScheduleRecord(arrive.get(date).getName(), dt.getTimeAsString(date)));
            }
        }

        if(departure.isEmpty()){
            departureList.add(new ScheduleRecord("There is no departures trains", ""));
        } else {
            for (Date date : departure.keySet()) {
                departureList.add(new ScheduleRecord(departure.get(date).getName(), dt.getTimeAsString(date)));
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

    public List<ScheduleRecord> getDepartureList() {
        return departureList;
    }

    public void setDepartureList(List<ScheduleRecord> departureList) {
        this.departureList = departureList;
    }

    public List<ScheduleRecord> getArriveList() {
        return arriveList;
    }

    public void setArriveList(List<ScheduleRecord> arriveList) {
        this.arriveList = arriveList;
    }

}
