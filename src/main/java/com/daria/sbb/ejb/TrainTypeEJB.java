package com.daria.sbb.ejb;

import com.daria.sbb.jpa.entities.TrainType;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Дарья on 07.04.2015.
 */

@Stateless
public class TrainTypeEJB {


    private static final Logger log = Logger.getLogger(TrainTypeEJB.class.getName());

    @PersistenceContext(unitName = "SBB_PU")
    private EntityManager entityManager;

    public List<TrainType> getAll() {
        TypedQuery<TrainType> query = entityManager.createNamedQuery("TrainType.getAll", TrainType.class);
        List<TrainType> list = query.getResultList();
        return list;
    }

    public TrainType addNew(TrainType trainType) {
        entityManager.merge(trainType);
        return trainType;
    }

    public boolean isExist(String trainName) {
        log.info(String.format("Start check train type with name: %s", trainName));
        TypedQuery<TrainType> queryOne = entityManager.createNamedQuery("TrainType.findByName", TrainType.class);
        queryOne.setParameter("type", trainName);
        List<TrainType> listOne = queryOne.getResultList();
        if (listOne.isEmpty()){
            log.info(String.format("No trains type with name %s", trainName));
            return false;
        } else {
            log.info(String.format("Train type with %s name have already exist", trainName));
            return true;
        }
    }

}
