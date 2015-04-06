package com.daria.sbb.jpa.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Дарья on 06.04.2015.
 */

@Entity
@Table(name = "stopstation")
public class StopStation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idStopStation;

    @Column(name = "Date")
    private Date date;

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
}
