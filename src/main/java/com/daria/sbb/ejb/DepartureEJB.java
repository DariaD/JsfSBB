package com.daria.sbb.ejb;

import com.daria.sbb.jpa.entities.Route;
import com.daria.sbb.jpa.entities.TrainDeparture;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Дарья on 10.04.2015.
 */
@Stateless
public class DepartureEJB {

    private static final Logger log = Logger.getLogger(DepartureEJB.class.getName());

    @PersistenceContext(unitName = "SBB_PU")
    private EntityManager entityManager;

    public List<TrainDeparture> getAll() {
        TypedQuery<TrainDeparture> query = entityManager.createNamedQuery("TrainDeparture.getAll", TrainDeparture.class);
        List<TrainDeparture> list = query.getResultList();
        return list;
    }


    public TrainDeparture addNew(TrainDeparture trainDeparture) {
        entityManager.merge(trainDeparture);
        return trainDeparture;
    }
}
