package com.daria.sbb.ejb;

import com.daria.sbb.jpa.entities.Station;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Дарья on 01.04.2015.
 */
@Stateless
public class StationEJB {

    private static final Logger log = Logger.getLogger(StationEJB.class.getName());

    @PersistenceContext(unitName = "SBB_PU")
    private EntityManager entityManager;

    public List<Station> findStation() {
        TypedQuery<Station> query = entityManager.createNamedQuery("Station.getAll", Station.class);
        List<Station> list = query.getResultList();
        return list;
    }


    public Station addNew(Station station) {
        entityManager.persist(station);
        return station;
    }

    public boolean isExist(String newStationName) {
        log.info("Start check station with name: " + newStationName);
        TypedQuery<Station> query = entityManager.createNamedQuery("Station.findByName", Station.class);
        query.setParameter("name", newStationName);
        List<Station> list = query.getResultList();
        if (list.isEmpty()){
            log.info(String.format("No station with name like %s", newStationName));
            return false;
        } else {
            log.info(String.format("Station with name %s exist in database", newStationName));
            return true;
        }
    }
}
