package com.daria.sbb.jpa.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Дарья on 04.03.2015.
 */

@Entity
@Table(name = "train")
@NamedQuery(name = "Train.getAll", query = "SELECT c from Train  c")
public class Train {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long idTrain;

        @Column(name = "name", length = 45)
        private String name;

        @Column(name = "plases")
        private int plases;

        public Train(String name, int plases) {
            this.name = name;
            this.plases = plases;
        }

        public Train() {}

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPlases() {
            return plases;
        }

        public void setPlases(int plases) {
            this.plases = plases;
        }

        public long getId() {
            return idTrain;
        }

        @Override
        public String toString() {
            return "Train{" +
                    "id=" + idTrain +
                    ", name='" + name +
                    ", place number=" + plases +
                    '}';
        }


//    @ManyToMany(mappedBy = "trains")
//    private Set<Station> stopStation;
//
//    public Set<Station> getStopStations() {
//        return stopStation;
//    }
//
//    public void setUsers(Set<Station> stopStation) {
//        this.stopStation = stopStation;
//    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "train")
    private Set<Schedule> stopStation;

    public Set<Schedule> getStopStation() {
        return stopStation;
    }

    public void setStopStation(Set<Schedule> stopStation) {
        this.stopStation = stopStation;
    }

    public void addSchedule(Schedule stop) {
        stopStation.add(stop);
    }


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "idTrainType", nullable = false)
    private TrainType trainType;

    public TrainType getTrainType() {
        return trainType;
    }

    public void setTrainType(TrainType trainType) {
        this.trainType = trainType;
    }


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "train")
    private Set<TrainDeparture> departures;


}
