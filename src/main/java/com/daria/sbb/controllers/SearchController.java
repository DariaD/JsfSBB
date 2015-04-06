package com.daria.sbb.controllers;

import com.daria.sbb.ejb.StationEJB;
import com.daria.sbb.jpa.entities.Station;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.*;

/**
 * Created by Дарья on 01.04.2015.
 */
@ManagedBean
public class SearchController {

    /*Parameters for search train and buy ticket*/
    private String selectedStationFrom;
    private String selectedStationTo;
    private Date date;
    private static Map<String, Station> stationMap = new TreeMap<String, Station>();

    /*Parameters for search for schedule*/
    private String selectedStationForTrain;
    private Date dateForTrain;

    @EJB
    private StationEJB stationEJB;


    public String getSelectedStationFrom() {
        return selectedStationFrom;
    }

    public void setSelectedStationFrom(String selectedStation) {
        this.selectedStationFrom = selectedStation;
    }
    public String getSelectedStationTo() {
        return selectedStationTo;
    }

    public void setSelectedStationTo(String selectedStation) {
        this.selectedStationTo = selectedStation;
    }

    public Map<String, Station> getStationMap() {
        List<Station> stationList = stationEJB.findStation();
        for (Station station : stationList) {
            stationMap.put(station.getName(), station);
        }

        return stationMap;
    }

    public void setStationMap(Map<String, Station> stationMap) {
        this.stationMap = stationMap;
    }

    public String search(){
        return "searchResult";

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateForTrain() {
        return dateForTrain;
    }

    public void setDateForTrain(Date dateForTrain) {
        this.dateForTrain = dateForTrain;
    }

    public String getSelectedStationForTrain() {
        return selectedStationForTrain;
    }

    public void setSelectedStationForTrain(String selectedStationForTrain) {
        this.selectedStationForTrain = selectedStationForTrain;
    }
}
