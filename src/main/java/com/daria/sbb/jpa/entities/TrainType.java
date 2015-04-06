package com.daria.sbb.jpa.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Дарья on 06.04.2015.
 */

@Entity
@Table(name = "traintype")
public class TrainType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idTrainType;

    @Column(name = "type", length = 45)
    private String type;

    @Column(name = "speed")
    private int speed;

    @Column(name = "costforkm", length = 45)
    private String costforkm;

    public TrainType() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getCostforkm() {
        return costforkm;
    }

    public void setCostforkm(String costforkm) {
        this.costforkm = costforkm;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "trainType")
    private Set<Train> trains;

    public Set<Train> getTrains() {
        return trains;
    }

    public void setTrains(Set<Train> trains) {
        this.trains = trains;
    }
}
