package com.daria.sbb.ejb;

import com.daria.sbb.jpa.entities.Station;
import com.daria.sbb.jpa.entities.StopStation;
import com.daria.sbb.jpa.entities.TrainDeparture;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * Created by Дарья on 10.04.2015.
 */

@Stateless
public class StopStationEJB {

    private static final Logger log = Logger.getLogger(StopStationEJB.class.getName());

    @PersistenceContext(unitName = "SBB_PU")
    private EntityManager entityManager;

    public List<StopStation> getAll() {
        TypedQuery<StopStation> query = entityManager.createNamedQuery("StopStation.getAll", StopStation.class);
        List<StopStation> list = query.getResultList();
        return list;
    }

    public List<StopStation> getByStation(Station station) {
        TypedQuery<StopStation> query = entityManager.createNamedQuery("StopStation.getByStation", StopStation.class);
        query.setParameter("station", station);
        List<StopStation> list = query.getResultList();
        return list;
    }


    public StopStation addNew(StopStation station) {
        entityManager.merge(station);
        //entityManager.persist(station);
        return station;
    }

    public List<StopStation> getByDepartureID(TrainDeparture trainDeparture) {
        TypedQuery<StopStation> query = entityManager.createNamedQuery("StopStation.getByDepartureID", StopStation.class);
        query.setParameter("trainDeparture", trainDeparture);
        List<StopStation> list = query.getResultList();
        return list;
    }
}
