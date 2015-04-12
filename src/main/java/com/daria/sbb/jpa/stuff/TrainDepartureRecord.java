package com.daria.sbb.jpa.stuff;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by Дарья on 12.04.2015.
 */
@ManagedBean
@SessionScoped
public class TrainDepartureRecord {
    private String name;
    private String date;
    private String type;
    private String from;
    private String to;

    public TrainDepartureRecord(String name, String date, String type, String from, String to) {
        this.name = name;
        this.date = date;
        this.type = type;
        this.from = from;
        this.to = to;
    }

    public TrainDepartureRecord() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
