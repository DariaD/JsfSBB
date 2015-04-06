package com.daria.sbb.controllers;

import com.daria.sbb.ejb.StationEJB;
import com.daria.sbb.jpa.entities.Station;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by Дарья on 03.04.2015.
 */

@ManagedBean
@SessionScoped
public class AdminManageController {

    String messages;
    String newStationName;


    @EJB
    StationEJB stationEJB = new StationEJB();

    public void addNewStation(){
        if(stationEJB.isExist(newStationName)){
            messages = "Such station already exist.";
        } else {
            Station newStation = new Station();
            newStation.setName(newStationName);
            stationEJB.addNew(newStation);
        }
    }

}
