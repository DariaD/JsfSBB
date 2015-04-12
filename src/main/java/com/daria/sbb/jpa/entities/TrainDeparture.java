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
        @NamedQuery(name = "TrainDeparture.getAll", query = "SELECT c from TrainDeparture  c"),
        @NamedQuery(name = "TrainDeparture.getById", query = "SELECT c FROM TrainDeparture c WHERE c.idTrainDeparture = :idTrainDeparture"),
        @NamedQuery(name = "TrainDeparture.getSingleOne", query = "SELECT c FROM TrainDeparture c WHERE c.stationFrom = :stationFrom AND  c.stationTo = :stationTo AND c.departureTime = :departureTime ")
})

public class TrainDeparture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTrainDeparture;

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


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "trainDeparture")
    private Set<Ticket> tickets = new HashSet<Ticket>();


    @Override
    public String toString() {
        return "Station{" +
                "id=" + idTrainDeparture +
                ", date='" + departureTime +
                ", stationFrom='" + stationFrom +
                ", stationTo='" + stationTo +
                ", train='" + train +
                ", stopStationsNumber = " + stopStations.size() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof TrainDeparture))//changed this from (getClass() != obj.getClass())
            return false;
        TrainDeparture other = (TrainDeparture) obj;
        if(this.getIdTrainDeparture() == other.getIdTrainDeparture()
//            && this.departureTime.equals(other.departureTime)
//            && this.train.equals(other.train)
                ){
            return true;
        }
        return false;
    }

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

    public int getIdTrainDeparture() {
        return idTrainDeparture;
    }

    public void setIdTrainDeparture(int idTrainDeparture) {
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

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

}
