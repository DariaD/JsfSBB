package com.daria.sbb.jpa.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Дарья on 04.03.2015.
 *
 * Describe station entity.
 * "Station" table from database
 * Connection to "Train" throw "schedule"
 *
 */
@Entity
       @Table(name = "station")
@NamedQueries({
        @NamedQuery(name = "Station.getAll", query = "SELECT c from Station c"),
        @NamedQuery(name="Station.findByName", query="SELECT c FROM Station c WHERE c.name = :name") })
public class Station {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long idStation;

        @Column(name = "Name", length = 100)
        private String name;

        public Station(String name) {
            this.name = name;
        }

        public Station() {}

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getId() {
            return idStation;
        }

        @Override
        public String toString() {
            return "Station{" +
                    "id=" + idStation +
                    ", name='" + name +
                    '}';
        }


//    @ManyToMany
//    @JoinTable(name="stopstation")
//    private Set<Train> trains;
//
//    public Set<Train> getTrain() {
//        return trains;
//    }
//
//    public void setTrain(Set<Train> trains) {
//        this.trains = trains;
//    }


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stationFrom")
    private Set<Schedule> stationFrom;

    public Set<Schedule> getScheduleFrom() {
        return stationFrom;
    }

    public void setScheduleFrom(Set<Schedule> stopStation) {
        this.stationFrom = stopStation;
    }

    public void addScheduleFrom(Schedule schedule) {
        stationFrom.add(schedule);
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stationTo")
    private Set<Schedule> stationTo;

    public Set<Schedule> getScheduleTo() {
        return stationTo;
    }

    public void setScheduleTo(Set<Schedule> stopStation) {
        this.stationTo = stopStation;
    }

    public void addScheduleTo(Schedule schedule) {
        stationTo.add(schedule);
    }



    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stationOne")
    private Set<Road> stationOne;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stationTwo")
    private Set<Road> stationTwo;

    public Set<Road> getStationOne() {
        return stationOne;
    }

    public void setStationOne(Set<Road> stationOne) {
        this.stationOne = stationOne;
    }

    public Set<Road> getStationTwo() {
        return stationTwo;
    }

    public void setStationTwo(Set<Road> stationTwo) {
        this.stationTwo = stationTwo;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "station")
    private Set<StopStation> stopStations;
}
