package com.daria.sbb.jpa.stuff;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by Дарья on 06.04.2015.
 */
@ManagedBean
@SessionScoped
public class RouteRecord {
    private String name = new String("");
    private int distance = 0;

    public RouteRecord(String name, int dis){
        setName(name);
        setDistance(dis);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
