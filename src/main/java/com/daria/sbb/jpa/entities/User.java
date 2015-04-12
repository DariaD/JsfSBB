package com.daria.sbb.jpa.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Дарья on 08.03.2015.
 */
@Entity
@Table(name = "User")
@NamedQueries({
        @NamedQuery(name = "User.getAll", query = "SELECT c FROM User  c"),
        @NamedQuery(name="User.findByName", query="SELECT c FROM User c WHERE c.login = :login"),
        @NamedQuery(name="User.findByLoginAndPassword", query="SELECT c FROM User c WHERE c.login = :login AND c.password = :password")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "secondName")
    private String secondName;

    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    public String getLogin(){return login;}

    public void setLogin(String login){this.login = login;}

    public String getPassword(){return password;}

    public void setPassword(String password){this.password = password;}

    public String getFirstName(){return firstName;}

    public void setFirstName(String firstName){this.firstName = firstName;}

    public String getSecondName(){return secondName;}

    public void setSecondName(String secondName){this.secondName = secondName;}

    public Date getDateOfBirth(){return dateOfBirth;}

    public void setDateOfBirth(Date date){this.dateOfBirth = date;}

    public long getId() {
        return idUser;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + idUser +
                ", login='" + login +
                ", first name=" + firstName +
                ", second name='" + secondName +
                ", date='" + dateOfBirth +
                '}';
    }



    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Ticket> tickets;

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setStationTo(Set<Ticket> tickets) {
        this.tickets = tickets;
    }


    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}