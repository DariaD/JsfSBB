package com.daria.sbb.ejb;

import com.daria.sbb.jpa.entities.StopStation;
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

    public List<StopStation> getByDateAndName(String name, Date date) {
        TypedQuery<StopStation> query = entityManager.createNamedQuery("StopStation.getByDateAndName", StopStation.class);
        query.setParameter("station", name);
        query.setParameter("date", date);
        List<StopStation> list = query.getResultList();
        return list;
    }


    public StopStation addNew(StopStation station) {
        entityManager.merge(station);
        return station;
    }

}
