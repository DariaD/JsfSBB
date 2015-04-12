package com.daria.sbb.controllers;

import com.daria.sbb.cdi.SessionUtil;
import com.daria.sbb.ejb.DepartureEJB;
import com.daria.sbb.ejb.StationEJB;
import com.daria.sbb.ejb.StopStationEJB;
import com.daria.sbb.ejb.TicketEJB;
import com.daria.sbb.jpa.entities.*;
import com.daria.utils.DateTime;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Дарья on 01.04.2015.
 */
@ManagedBean(name = "searchController")
@SessionScoped
public class SearchController  implements Serializable {

    private static final Logger log = Logger.getLogger(SearchController.class.getName());

    /*Parameters for search train and buy ticket*/
    private Station selectedStationFrom;
    private Station selectedStationTo;
    private Date date;
    private Map<String, Station> stationMap = new TreeMap<String, Station>();

    private List<String> showList = new ArrayList<String>();
    private List<TrainDeparture> suitableTrain = new  ArrayList<TrainDeparture>();
    private Map<String, TrainDeparture> suitableTrainMap = new  TreeMap<String, TrainDeparture>();

    private TrainDeparture selectedTrain;

    DateTime dt = new DateTime();
    SessionUtil sessionUtil = new SessionUtil();

    /*Parameters for search for schedule*/
    private String selectedStationForTrain;
    private Date dateForTrain;


    /*Parameters for confirm page*/
    private String departureDate;
    private String arriveDate;
    private String timeOfJuorney;
    private int price;
    private User user;

    private int availablePlaces;
    private String dateOfBirth;
    private String message;


    @EJB
    private StationEJB stationEJB;
    @EJB
    private StopStationEJB stopStationEJB;
    @EJB
    private DepartureEJB departureEJB;
    @EJB
    private TicketEJB ticketEJB = new TicketEJB();


    public String search() throws IOException {
        showList.clear();
        suitableTrainMap.clear();
        List<StopStation> listStationFrom = stopStationEJB.getByStation(selectedStationFrom);
        List<StopStation> listStationTo = stopStationEJB.getByStation(selectedStationTo);
        if(listStationFrom.isEmpty() || listStationTo.isEmpty() || selectedStationFrom.getName().equals(selectedStationTo.getName())){
            FacesMessage msg = new FacesMessage("There is no trains for you request", "Please enter another destination or date.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            return "nosuchtrain";
        }

        for(StopStation stopStationFrom : listStationFrom){
            if(!dt.equelsByDate(stopStationFrom.getDate(), date)){
                log.info("No such date!!!");
                log.info(" Request date: " + date);
                log.info(" SpotDate date: " + stopStationFrom.getDate());
                continue;
            }
            for(StopStation stopStationTo : listStationTo){
                if(stopStationFrom.getTrainDeparture().getIdTrainDeparture() == stopStationTo.getTrainDeparture().getIdTrainDeparture()
                        && stopStationFrom.getDate().before(stopStationTo.getDate())){
                    List<TrainDeparture> TrainDepartureList = departureEJB.getById(stopStationFrom.getTrainDeparture().getIdTrainDeparture());
                    log.info("Find common train for both stations: " + stopStationFrom.getTrainDeparture().getIdTrainDeparture());
                    if(!TrainDepartureList.isEmpty()){
                        TrainDeparture trainDeparture = TrainDepartureList.get(0);
                        suitableTrain.add(trainDeparture);
                        int distance = stopStationTo.getDistanceFromStart() - stopStationFrom.getDistanceFromStart();
                        double timeInJurney = ((double) distance) / trainDeparture.getTrain().getTrainType().getSpeed();

                        String trainDepartureToUser = String.format("%s \n Train: %s, %s - %s, \n Arrive: %s, \n Number of free places: %s \n Time of journey: %s, \n Price: %s tugriks",
                                stopStationFrom.getDate(), trainDeparture.getTrain().getName(), trainDeparture.getStationFrom().getName(), trainDeparture.getStationTo().getName(),
                                stopStationTo.getDate(), getMinNumberOfPlace(trainDeparture) , dt.createTimeStringFromDouble(timeInJurney), distance*trainDeparture.getTrain().getTrainType().getCostforkm());
                        suitableTrainMap.put(trainDepartureToUser, trainDeparture);
                        log.info("Suitable Train: " + trainDeparture);
                    } else {
                        log.error("Cunt find Train Departure by ID");
                    }

                }
            }
        }
        HttpSession session = sessionUtil.getSession();
        session.setAttribute("trainMap", suitableTrainMap);
        for(String str : suitableTrainMap.keySet()){
            showList.add(str);
        }
        log.info("All Suitable Train are found.");
        return "searchResult";
    }


    public String byTicket() throws IOException {
        HttpSession session = sessionUtil.getSession();
        setUser((User) session.getAttribute("user"));
        setDateOfBirth(dt.getDateAsString(user.getDateOfBirth()));
        log.info("Selected Train: " + selectedTrain);
        if(session.getAttribute("user") == null) {
            log.info("redirect to index");
          return "index";
       }

        TrainDeparture selectedTrainDeparture = selectedTrain;
        log.info("Mapping okay: " + selectedTrainDeparture.toString());
        StopStation from = stopStationEJB.getbyTrainAndStation(selectedTrain,selectedStationFrom);
        StopStation to = stopStationEJB.getbyTrainAndStation(selectedTrain, selectedStationTo);

        Date currentDate = new Date();
        Date ableDate = dt.addTenMin(currentDate);

        if(from.getDate().before(ableDate) || to.getDate().before(ableDate)){
            FacesMessage msg = new FacesMessage("There is no time to get this train", "You can't buy a ticket for train in the past or less than 10 min before departure. Please select another train.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            log.info("No time to get this train");
            return "";
        }
            setDepartureDate(dt.getDateTimeAsString(from.getDate()));
            setArriveDate(dt.getDateTimeAsString(to.getDate()));

            int distance = Math.abs(from.getDistanceFromStart() - to.getDistanceFromStart());
            double timeInJurney = ((double) distance) / selectedTrainDeparture.getTrain().getTrainType().getSpeed();
            setTimeOfJuorney(dt.createTimeStringFromDouble(timeInJurney));
            int price = distance * selectedTrainDeparture.getTrain().getTrainType().getCostforkm();
            setPrice(price);
            log.info("Success train");
            return "successBothTicket";
    }

    public String confirm() throws IOException {
        log.info("Confirm button pressed");
        List<StopStation> listStationFrom = stopStationEJB.getByDepartureID(selectedTrain);
        for (StopStation stopStation : listStationFrom) {
            if(!stopStation.getStation().equals(selectedTrain.getStationTo())) {
                stopStation.setCurrPlaceAvalable(stopStation.getCurrPlaceAvalable() - 1);
                stopStationEJB.addNew(stopStation);
            }
        }
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setTrainDeparture(selectedTrain);
        ticketEJB.addNew(ticket);
        return "success";
    }

    public String back() throws IOException {
        log.info("Confirm button pressed");
        return "back";
    }


    private int getMinNumberOfPlace(TrainDeparture trainDeparture) {
        List<StopStation> listStationFrom = stopStationEJB.getByDepartureID(trainDeparture);
        int res = listStationFrom.get(0).getCurrPlaceAvalable();
        for (StopStation stopStation : listStationFrom) {
            if(!stopStation.getStation().equals(trainDeparture.getStationTo()) && res < stopStation.getCurrPlaceAvalable()){
                res = stopStation.getCurrPlaceAvalable();
            }
        }
        return res;
    }

    public Map<String, Station> getStationMap() {
        List<Station> stationList = stationEJB.findStation();
        for (Station station : stationList) {
            stationMap.put(station.getName(), station);
        }
        return stationMap;
    }

    public void setStationMap(Map<String, Station> stationMap) {
        this.stationMap = stationMap;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateForTrain() {
        return dateForTrain;
    }

    public void setDateForTrain(Date dateForTrain) {
        this.dateForTrain = dateForTrain;
    }

    public String getSelectedStationForTrain() {
        return selectedStationForTrain;
    }

    public void setSelectedStationForTrain(String selectedStationForTrain) {
        this.selectedStationForTrain = selectedStationForTrain;
    }

    public void setSelectedStationTo(Station selectedStationTo) {
        this.selectedStationTo = selectedStationTo;
    }

    public void setSelectedStationFrom(Station selectedStationFrom) {
        this.selectedStationFrom = selectedStationFrom;
    }

    public Station getSelectedStationFrom() {
        return selectedStationFrom;
    }

    public Station getSelectedStationTo() {
        return selectedStationTo;
    }

    public List<TrainDeparture> getSuitableTrain() {
        return suitableTrain;
    }

    public void setSuitableTrain(List<TrainDeparture> suitableTrain) {
        this.suitableTrain = suitableTrain;
    }

    public TrainDeparture getSelectedTrain() {
        return selectedTrain;
    }

    public void setSelectedTrain(TrainDeparture selectedTrain) {
        this.selectedTrain = selectedTrain;
    }

    public List<String> getShowList() {
        return showList;
    }

    public void setShowList(List<String> showList) {
        this.showList = showList;
    }

    public Map<String, TrainDeparture> getSuitableTrainMap() {
        return suitableTrainMap;
    }

    public void setSuitableTrainMap(Map<String, TrainDeparture> suitableTrainMap) {
        this.suitableTrainMap = suitableTrainMap;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(String arriveDate) {
        this.arriveDate = arriveDate;
    }

    public String getTimeOfJuorney() {
        return timeOfJuorney;
    }

    public void setTimeOfJuorney(String timeOfJuorney) {
        this.timeOfJuorney = timeOfJuorney;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(int availablePlaces) {
        this.availablePlaces = availablePlaces;
    }


    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
