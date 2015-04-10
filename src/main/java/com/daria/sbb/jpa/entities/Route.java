package com.daria.sbb.jpa.entities;

import javax.persistence.*;

/**
 * Created by Дарья on 06.04.2015.
 */

@Entity
@Table(name = "roads")
@NamedQueries({
        @NamedQuery(name = "Route.getAll", query = "SELECT c from Route c"),
        @NamedQuery(name = "Route.findByNames",
                query="SELECT c FROM Route c WHERE c.stationOne = :stationOne and c.stationTwo = :stationTwo") })

public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRoads;

    @Column(name = "distance")
    private int distance;



    /*One station can be in several schedules*/
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "idStationOne", nullable = false)
    private Station stationOne;

    /*One station can be in several schedules*/
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "idStationTwo", nullable = false)
    private Station stationTwo;

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Station getStationTwo() {
        return stationTwo;
    }

    public void setStationTwo(Station stationTwo) {
        this.stationTwo = stationTwo;
    }

    public Station getStationOne() {
        return stationOne;
    }

    public void setStationOne(Station stationOne) {
        this.stationOne = stationOne;
    }


}
