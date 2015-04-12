package com.daria.sbb.jpa.entities;

import javax.persistence.*;

/**
 * Created by Дарья on 08.03.2015.
 *
 * Describe train entity.
 * "Train" table from database
 *
 */

@Entity
@Table(name = "ticket")
@NamedQueries({
        @NamedQuery(name = "Ticket.getAll", query = "SELECT c from Ticket  c"),
        @NamedQuery(name="Ticket.findByUserAndTrain", query="SELECT c FROM Ticket c WHERE c.user = :user AND c.trainDeparture =:trainDeparture"),
        @NamedQuery(name="Ticket.findByDeparture", query="SELECT c FROM Ticket c WHERE c.trainDeparture =:trainDeparture")
})
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTicket;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "IdDeparture", nullable = false)
    private TrainDeparture trainDeparture;

    public TrainDeparture getTrainDeparture() {
        return trainDeparture;
    }

    public void setTrainDeparture(TrainDeparture trainDeparture) {
        this.trainDeparture = trainDeparture;
    }



    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ticket() {}

    public long getId() {
        return idTicket;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + idTicket +
                "User =" + user.toString() +
                '}';
    }
}
