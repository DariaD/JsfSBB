package com.daria.sbb.controllers;

/**
 * Created by Дарья on 11.04.2015.
 */
        import com.daria.sbb.cdi.SessionUtil;
        import com.daria.sbb.ejb.DepartureEJB;
        import com.daria.sbb.ejb.StationEJB;
        import com.daria.sbb.ejb.StopStationEJB;
        import com.daria.sbb.jpa.entities.Station;
        import com.daria.sbb.jpa.entities.StopStation;
        import com.daria.sbb.jpa.entities.TrainDeparture;
        import com.daria.sbb.jpa.entities.User;
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

@ManagedBean(name = "conformitionController")
@SessionScoped
public class ConformitionController  implements Serializable {

    private static final Logger log = Logger.getLogger(ConformitionController.class.getName());

    private List<TrainDeparture> suitableTrain = new  ArrayList<TrainDeparture>();
    private Map<String, TrainDeparture> suitableTrainMap = new  TreeMap<String, TrainDeparture>();

    private TrainDeparture selectedTrain;

    DateTime dt = new DateTime();
    SessionUtil sessionUtil = new SessionUtil();

    /*Parameters for confirm page*/
    private String departureDate;
    private String arriveDate;
    private String timeOfJuorney;
    private int price;
    private User user;

    private String message;


    @EJB
    private StationEJB stationEJB;
    @EJB
    private StopStationEJB stopStationEJB;
    @EJB
    private DepartureEJB departureEJB;


    public String byTicket() throws IOException {
        log.info("Selected Train: " + selectedTrain);
        if(selectedTrain == null){
            return "";
        }
        HttpSession session = sessionUtil.getSession();
        Map<String, TrainDeparture> suitableTrainMap = (Map<String, TrainDeparture>) session.getAttribute("trainMap");
        if(suitableTrainMap.keySet().contains(selectedTrain)) {
            TrainDeparture selectedTrainDeparture = suitableTrainMap.get(selectedTrain);
            log.info("Mapping okay: " + selectedTrainDeparture.toString());
            Object[] stopStations = selectedTrainDeparture.getStopStations().toArray();
            StopStation from = (StopStation) stopStations[0];
            StopStation to = (StopStation) stopStations[1];
            if (from.getDate().before(to.getDate())) {
                setDepartureDate(from.getDate().toString());
                setArriveDate(to.getDate().toString());
            } else {
                setDepartureDate(to.getDate().toString());
                setArriveDate(from.getDate().toString());
            }
            int distance = Math.abs(from.getDistanceFromStart() - to.getDistanceFromStart());
            double timeInJurney = ((double) distance) / selectedTrainDeparture.getTrain().getTrainType().getSpeed();
            setTimeOfJuorney(dt.createTimeStringFromDouble(timeInJurney));
            int price = distance * selectedTrainDeparture.getTrain().getTrainType().getCostforkm();
            setPrice(price);
            log.info("Success train");
            FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/JavaServerFaces/pages/user/ConfirmBothTicket.xhtml");
            return "successBothTicket";
        }else{
            setMessage("Sorry. There is some problem to connection. Please, select another train oк try again later.");
            FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/JavaServerFaces/pages/user/FailBothTicket.xhtml");
            return "fail";
        }
    }

    public String confirm() throws IOException {
        log.info("Confirm button pressed");
        FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/JavaServerFaces/pages/user/SuccessBothTicket.xhtml");
        return "success";
    }

    public String back() throws IOException {
        log.info("Confirm button pressed");
        FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/JavaServerFaces/pages/user/searchTrain.xhtml");
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
        HttpSession session = sessionUtil.getSession();
        user = (User) session.getAttribute("user");
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
}
