package com.daria.sbb.controllers.admin;

import com.daria.sbb.ejb.TrainEJB;
import com.daria.sbb.ejb.TrainTypeEJB;
import com.daria.sbb.jpa.entities.Train;
import com.daria.sbb.jpa.entities.TrainType;
import com.daria.sbb.jpa.stuff.TrainRecord;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.*;

/**
 * Created by Дарья on 07.04.2015.
 */

@ManagedBean
@SessionScoped
public class TrainController {

    private static final Logger log = Logger.getLogger(TrainController.class.getName());

    @EJB
    TrainEJB trainEJB = new TrainEJB();
    @EJB
    TrainTypeEJB trainTypeEJB = new TrainTypeEJB();

    private String name;
    private int places;
    private TrainType selectedType;
    private TreeSet<String> trains = new TreeSet<String>();
    private List<TrainRecord> trainList = new ArrayList<TrainRecord>();
    private Map<String, TrainType> typeMap = new TreeMap<String, TrainType>();
    private int idTrain = 0;

    public String addTrain(){
        Train train = new Train();
        String message;
        if( name!=null && places > 0) {
            train.setName(name);
            train.setPlaces(places);
            train.setTrainType(selectedType);
            selectedType.addTrain(train);
            trainEJB.addNew(train);
            message = "Train successfully added.";
            log.info(message);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, message, null);
            facesContext.addMessage(null, facesMessage);
            return "";
        } else {
            message = "Invalid data. Please try again.";
            log.info(message);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
            facesContext.addMessage(null, facesMessage);
            return "";
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }


    public void setSelectedType(TrainType selectedType) {
        this.selectedType = selectedType;
    }

    public  TrainType getSelectedType(){
        return this.selectedType;
    }

    public Map<String, TrainType> getTypeMap() {
        List<TrainType> trainTypeList = trainTypeEJB.getAll();
        for (TrainType trainType : trainTypeList) {
            typeMap.put(trainType.getType(), trainType);
        }

        return typeMap;
    }

    public void setTypeMap(Map<String, TrainType> typeMap) {
        this.typeMap = typeMap;
    }

    public TreeSet<String> getTrains() {
        List<Train> trainsFromDB = trainEJB.getAll();
        for (Train station : trainsFromDB){
            trains.add(station.getName());
        }
        return trains;
    }

    public void setTrains(TreeSet<String> trains) {
        this.trains = trains;
    }

    public int getIdTrain() {
        setIdTrain(idTrain + 1);
        return idTrain;
    }

    public void setIdTrain(int idTrain) {
        this.idTrain = idTrain;
    }

    public List<TrainRecord> getTrainList() {
        trainList.clear();
        setIdTrain(0);
        TreeMap<String, Train> trains = new TreeMap<String, Train>();
        List<Train> all = trainEJB.getAll();
        for (Train train : all) {
            trains.put(train.getName(), train);
        }
        for(Map.Entry<String, Train> entry : trains.entrySet()){
            Train value = entry.getValue();
            TrainType trainType = value.getTrainType();
            trainList.add(new TrainRecord(entry.getKey(), value.getPlaces(), trainType.getType(), trainType.getSpeed(), trainType.getCostforkm()));
        }
        return trainList;
    }

    public void setTrainList(List<TrainRecord> trainList) {
        this.trainList = trainList;
    }
}
