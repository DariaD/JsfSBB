package com.daria.sbb.jpa.entities;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.HashSet;
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
        @NamedQuery(name = "Station.findByName", query = "SELECT c FROM Station c WHERE c.name = :name") })
public class Station {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
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

        @Override
         public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (!(obj instanceof Station))//changed this from (getClass() != obj.getClass())
                return false;
            Station other = (Station) obj;
            if(this.getId() == other.getId()
                   // && this.getName().equals(other.getName())
                    ){
                return true;
            }
            return false;
        }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(idStation).toHashCode();
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stationOne")
    private Set<Route> stationOne;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stationTwo")
    private Set<Route> stationTwo;

//
//    public Set<TrainDeparture> getStationsFormList() {
//        return stationsFormList;
//    }
//
//    public void setStationsFormList(Set<TrainDeparture> stationsFormList) {
//        this.stationsFormList = stationsFormList;
//    }
//
//    public Set<TrainDeparture> getStationToList() {
//        return stationToList;
//    }
//
//    public void setStationToList(Set<TrainDeparture> stationToList) {
//        this.stationToList = stationToList;
//    }
//
//    public Set<StopStation> getStopStations() {
//        return stopStations;
//    }
//
//    public void setStopStations(Set<StopStation> stopStations) {
//        this.stopStations = stopStations;
//    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stationFrom")
    private Set<TrainDeparture> stationsFormList = new HashSet<TrainDeparture>();

//    public void addStopForm(TrainDeparture ss){
//        stationsFormList.add(ss);
//    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stationTo")
    private Set<TrainDeparture> stationToList = new HashSet<TrainDeparture>();
//
//    public void addStopTo(TrainDeparture ss){
//        stationToList.add(ss);
//    }
//
//    public Set<Route> getStationOne() {
//        return stationOne;
//    }
//
//    public void setStationOne(Set<Route> stationOne) {
//        this.stationOne = stationOne;
//    }
//
//
//
//    public Set<Route> getStationTwo() {
//        return stationTwo;
//    }
//
//    public void setStationTwo(Set<Route> stationTwo) {
//        this.stationTwo = stationTwo;
//    }
//
//    public void addStationOne(Route route) {
//        stationOne.add(route);
//    }
//
//    public void addStationTwo(Route route) {
//        stationTwo.add(route);
//    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "station")
    private Set<StopStation> stopStations;

//    public void addStopStation(StopStation stopStation) {
//        stopStations.add(stopStation);
//    }
}
