package com.daria.sbb.jpa.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Дарья on 06.04.2015.
 */

@Entity
@Table(name = "traintype")
@NamedQueries({
        @NamedQuery(name = "TrainType.getAll", query = "SELECT c from TrainType  c"),
        @NamedQuery(name="TrainType.findByName", query="SELECT c FROM TrainType c WHERE c.type = :type")
})
public class TrainType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTrainType;

    @Column(name = "type", length = 45)
    private String type;

    @Column(name = "speed")
    private int speed;

    @Column(name = "costforkm", length = 45)
    private int costforkm;

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

    public int getCostforkm() {
        return costforkm;
    }

    public void setCostforkm(int costforkm) {
        this.costforkm = costforkm;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "trainType")
    private Set<Train> trains;

    public void addTrain(Train train){
        trains.add(train);
    }

    public Set<Train> getTrains() {
        return trains;
    }

    public void setTrains(Set<Train> trains) {
        this.trains = trains;
    }

    public long getIdTrainType() {
        return idTrainType;
    }

    public void setIdTrainType(long idTrainType) {
        this.idTrainType = idTrainType;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof TrainType))//changed this from (getClass() != obj.getClass())
            return false;
        TrainType other = (TrainType) obj;
        if(this.getIdTrainType() == other.getIdTrainType() && this.type.equals(other.getType())){
            return true;
        }
        return false;
    }
}
