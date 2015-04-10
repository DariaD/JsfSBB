package com.daria.sbb.jpa.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Дарья on 06.04.2015.
 */

@Entity
@Table(name = "trainDeparture")
@NamedQueries({
        @NamedQuery(name = "TrainDeparture.getAll", query = "SELECT c from TrainDeparture  c")
})

public class TrainDeparture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTrainDeparture;

    @Column(name = "DepartureTime")
    private Date departureTime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "idDepStationFrom", nullable = false)
    private Station stationFrom;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "idDepStationTo", nullable = false)
    private Station stationTo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "idTrain", nullable = false)
    private Train train;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "trainDeparture")
    private Set<StopStation> stopStations = new HashSet<StopStation>();

    public void addStopStation(StopStation ss){
        stopStations.add(ss);
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }


    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Set<StopStation> getStopStations() {
        return stopStations;
    }

    public void setStopStations(Set<StopStation> stopStations) {
        this.stopStations = stopStations;
    }

    public long getIdTrainDeparture() {
        return idTrainDeparture;
    }

    public void setIdTrainDeparture(long idTrainDeparture) {
        this.idTrainDeparture = idTrainDeparture;
    }

    public Station getStationFrom() {
        return stationFrom;
    }

    public void setStationFrom(Station stationFrom) {
        this.stationFrom = stationFrom;
    }

    public Station getStationTo() {
        return stationTo;
    }

    public void setStationTo(Station stationTo) {
        this.stationTo = stationTo;
    }
}
