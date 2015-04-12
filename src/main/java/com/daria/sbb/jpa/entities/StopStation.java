package com.daria.sbb.jpa.entities;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Дарья on 06.04.2015.
 */

@Entity
@Table(name = "stopstation")

@NamedQueries({
        @NamedQuery(name = "StopStation.getAll", query = "SELECT c from StopStation c"),
        @NamedQuery(name = "StopStation.getByStation", query = "SELECT c FROM StopStation c WHERE c.station = :station"),
        @NamedQuery(name = "StopStation.getbyTrainAndStation", query = "SELECT c FROM StopStation c WHERE c.station = :station AND c.trainDeparture = :trainDeparture"),
        @NamedQuery(name = "StopStation.getByDepartureID", query = "SELECT c FROM StopStation c WHERE c.trainDeparture = :trainDeparture")
})
public class StopStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idStopStation;

    @Column(name = "Date")
    private Date date;

    @Column(name = "CurrPlaceAvalable")
    private int currPlaceAvalable;

    @Column(name = "distanceFromStart")
    private int distanceFromStart;

    /*One station can be in several schedules*/
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "idStation", nullable = false)
    private Station station;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "idDepartureTrain", nullable = false)
    private TrainDeparture trainDeparture;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public TrainDeparture getTrainDeparture() {
        return trainDeparture;
    }

    public void setTrainDeparture(TrainDeparture trainDeparture) {
        this.trainDeparture = trainDeparture;
    }

    public int getCurrPlaceAvalable() {
        return currPlaceAvalable;
    }

    public void setCurrPlaceAvalable(int currPlaceAvalable) {
        this.currPlaceAvalable = currPlaceAvalable;
    }

    public long getIdStopStation() {
        return idStopStation;
    }

    public void setIdStopStation(long idStopStation) {
        this.idStopStation = idStopStation;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof StopStation))//changed this from (getClass() != obj.getClass())
            return false;
        StopStation other = (StopStation) obj;
        if(this.getIdStopStation() == other.getIdStopStation()
            //&& this.getStation().equals(other.getStation())
//           && this.getDate().equals(other.getDate())
        ){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 39).append(idStopStation).toHashCode();
    }

    @Override
    public String toString(){
        return "StopStation{" +
                "id=" + idStopStation +
                ", date='" + date +
                ", currPlaceAvalable='" + currPlaceAvalable +
                ", trainDeparture='" + trainDeparture.toString() +
                ", station='" + station.toString() +
                '}';

    }

    public int getDistanceFromStart() {
        return distanceFromStart;
    }

    public void setDistanceFromStart(int distanceFromStart) {
        this.distanceFromStart = distanceFromStart;
    }
}
