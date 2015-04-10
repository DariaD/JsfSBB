package com.daria.sbb.jpa.primfaceServices;

import com.daria.sbb.ejb.TrainTypeEJB;
import com.daria.sbb.jpa.entities.TrainType;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by Дарья on 07.04.2015.
 */


@ManagedBean(name="typeService", eager = true)
@ApplicationScoped
public class TypeService {

    private List<TrainType> types;

    @EJB
    TrainTypeEJB ttEJB = new TrainTypeEJB();

    @PostConstruct
    public void init() {
        types = ttEJB.getAll();
    }

    public List<TrainType> getTypes() {
        return types;
    }

    public TrainType getTrainTypeByID(int i) {
        for (TrainType type : types) {
            if(type.getIdTrainType() == i){
                return type;
            }
        }
        return null;
    }
}