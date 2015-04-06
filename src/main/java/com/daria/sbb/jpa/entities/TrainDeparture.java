package com.daria.sbb.jpa.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Дарья on 06.04.2015.
 */

@Entity
@Table(name = "trainDeparture")
public class TrainDeparture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idTrainDeparture;

    @Column(name = "DepartureTime")
    private Date departureTime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "idTrain", nullable = false)
    private Train train;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "trainDeparture")
    private Set<StopStation> stopStations;

}
