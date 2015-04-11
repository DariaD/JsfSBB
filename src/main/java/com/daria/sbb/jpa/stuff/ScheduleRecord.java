package com.daria.sbb.jpa.stuff;

/**
 * Created by Дарья on 10.04.2015.
 */
public class ScheduleRecord {
    String stationName;
    String date;

    public ScheduleRecord(String stationName, String date){
        this.stationName = stationName;
        this.date = date;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
