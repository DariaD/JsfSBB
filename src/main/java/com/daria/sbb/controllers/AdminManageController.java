package com.daria.sbb.controllers;

import com.daria.sbb.ejb.RouteEJB;
import com.daria.sbb.ejb.StationEJB;
import com.daria.sbb.jpa.entities.Route;
import com.daria.sbb.jpa.entities.Station;
import com.daria.sbb.jpa.stuff.RouteRecord;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.io.SerializablePermission;
import java.util.*;

/**
 * Created by Дарья on 03.04.2015.
 */

@ManagedBean
@SessionScoped
public class AdminManageController implements Serializable {

    private String messages;
    private String newStationName;
    private int idStation = 0;
    private int idRoute = 0;
    private TreeSet<String> stations = new TreeSet<String>();
    private List<RouteRecord> routesList = new ArrayList<RouteRecord>();
    private Map<String, Station> stationMap = new TreeMap<String, Station>();

    private Station stationOne;
    private Station stationTwo;
    private String distance;


    @EJB
    StationEJB stationEJB = new StationEJB();
    @EJB
    RouteEJB routeEJB = new RouteEJB();

    public void addNewRoute(){
        if(routeEJB.isExist(stationOne, stationTwo)){
            messages = "Such route already exist.";
        } else {
            Route newRoute = new Route();
//            Station st1 = stationEJB.findByName(stationOne);
//            Station st2 = stationEJB.findByName(stationTwo);
            stationOne.addStationTwo(newRoute);
            stationTwo.addStationTwo(newRoute);
            newRoute.setStationOne(stationOne);
            newRoute.setStationTwo(stationTwo);
//            try {
                int dist = Integer.parseInt(distance);
                newRoute.setDistance(dist);
                routeEJB.addNew(newRoute);
                messages = "Route add successfully";
  //          } catch ()

        }
    }

    public void addNewStation(){
        if(stationEJB.isExist(newStationName)){
            messages = "Such station already exist.";
        } else {
            Station newStation = new Station();
            newStation.setName(newStationName);
            stationEJB.addNew(newStation);
            messages = "Station add successfully";
        }
    }

    public void setNewStationName(String newStationName) {
        this.newStationName = newStationName;
    }

    public String getNewStationName() {
        return newStationName;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public TreeSet<String> getStations() {
        List<Station> stationsFromDB = stationEJB.findStation();
        for (Station station : stationsFromDB){
            stations.add(station.getName());
        }
        return stations;
    }


    public void setStations(TreeSet<String> stations) {
        this.stations = stations;
    }

    public int getIdStation() {
        setIdStation(idStation + 1);
        return idStation;
    }

    public void setIdStation(int id) {
        this.idStation = id;
    }

    public Station getStationOne() {
        return stationOne;
    }

    public void setStationOne(Station stationOne) {
        this.stationOne = stationOne;
    }

    public Station getStationTwo() {
        return stationTwo;
    }

    public void setStationTwo(Station stationTwo) {
        this.stationTwo = stationTwo;
    }

    public int getIdRoute() {
        setIdRoute(idRoute + 1);
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    public List<RouteRecord> getRoutesList() {
        routesList.clear();
        setIdRoute(0);
        TreeMap<String, Integer> routes = new TreeMap<String, Integer>();
        List<Route> all = routeEJB.getAll();
        for (Route route : all) {
            String key1 = String.format("%s - %s", route.getStationOne().getName(), route.getStationTwo().getName());
            String key2 = String.format("%s - %s", route.getStationTwo().getName(), route.getStationOne().getName());

            routes.put(key1, route.getDistance());
            routes.put(key2, route.getDistance());
        }
        for(Map.Entry<String, Integer> entry : routes.entrySet()){
            routesList.add(new RouteRecord(entry.getKey(), entry.getValue()));
        }

        return routesList;
    }

    public void setRoutesList(List<RouteRecord> routesList) {
        this.routesList = routesList;
    }


    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
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
}
