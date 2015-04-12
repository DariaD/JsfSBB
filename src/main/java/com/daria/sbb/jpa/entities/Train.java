package com.daria.sbb.jpa.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Дарья on 04.03.2015.
 */

@Entity
@Table(name = "train")
@NamedQueries({
    @NamedQuery(name = "Train.getAll", query = "SELECT c from Train  c"),
    @NamedQuery(name = "Train.findByName", query = "SELECT c FROM Train c WHERE c.name = :name")
})
public class Train {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long idTrain;

        @Column(name = "name", length = 45)
        private String name;

        @Column(name = "places")
        private int places;

        public Train(String name, int places) {
            this.name = name;
            this.places = places;
        }

        public Train() {}

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPlaces() {
            return places;
        }

        public void setPlaces(int places) {
            this.places = places;
        }

        public long getId() {
            return idTrain;
        }

        @Override
        public String toString() {
            return "Train{" +
                    "id=" + idTrain +
                    ", name='" + name +
                    ", place number=" + places +
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


    public void addTrainDeparture(TrainDeparture trainDeparture) {
        departures.add(trainDeparture);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Train))//changed this from (getClass() != obj.getClass())
            return false;
        Train other = (Train) obj;
        if(this.getId() == other.getId()
               // && this.getName().equals(other.getName())
                ){
            return true;
        }
        return false;
    }
}
