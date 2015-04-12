package com.daria.sbb.jpa.stuff;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by Дарья on 12.04.2015.
 */

@ManagedBean
@SessionScoped
public class UserRecord {

    private String train;
    private String departureDate;
    private String userName;
    private String dateOfBirth;

    public UserRecord(String train, String departureDate, String userName, String dateOfBirth) {
        this.train = train;
        this.userName = userName;
        this.departureDate = departureDate;
        this.dateOfBirth = dateOfBirth;
    }

    public UserRecord() { }
    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }



    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
