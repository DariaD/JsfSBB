package com.daria.sbb.ejb;

import com.daria.sbb.jpa.entities.Station;
import com.daria.sbb.jpa.entities.Train;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Дарья on 06.04.2015.
 */
@Stateless
public class TrainEJB {

    private static final Logger log = Logger.getLogger(TrainEJB.class.getName());

    @PersistenceContext(unitName = "SBB_PU")
    private EntityManager entityManager;

    public List<Train> getAll() {
        TypedQuery<Train> query = entityManager.createNamedQuery("Train.getAll", Train.class);
        List<Train> list = query.getResultList();
        return list;
    }

    public Train addNew(Train train) {
        //entityManager.persist(route);
        entityManager.merge(train);
        return train;
    }

    public boolean isExist(String trainName) {
        log.info(String.format("Start check train with name: %s", trainName));
        TypedQuery<Train> queryOne = entityManager.createNamedQuery("Train.findByName", Train.class);
        queryOne.setParameter("name", trainName);
        List<Train> listOne = queryOne.getResultList();
        if (listOne.isEmpty()){
            log.info(String.format("No trains with name %s", trainName));
            return false;
        } else {
            log.info(String.format("Train with %s name have already exist", trainName));
            return true;
        }
    }

}

