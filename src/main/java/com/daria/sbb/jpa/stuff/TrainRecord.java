package com.daria.sbb.jpa.stuff;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by Дарья on 07.04.2015.
 */
@ManagedBean
@SessionScoped
public class TrainRecord {
    private String name;
    private int places;
    private String type;
    private int speed;
    private int cost;

    public TrainRecord(String name, int places, String type, int speed, int cost){
        setName(name);
        setPlaces(places);
        setCost(cost);
        setSpeed(speed);
        setType(type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
