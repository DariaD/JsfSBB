package com.controllers;

import com.ejb.StationEJB;
import com.jpa.entities.Station;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.*;

/**
 * Created by Дарья on 01.04.2015.
 */
@ManagedBean
public class SearchController {

    private String selectedStationFrom;
    private String selectedStationTo;
    private Date date;
    private static Map<String, Station> stationMap = new TreeMap<String, Station>();

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
        return "serchResult";

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
