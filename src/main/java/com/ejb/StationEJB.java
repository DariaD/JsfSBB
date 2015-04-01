package com.ejb;

import com.jpa.entities.Station;
import com.jpa.entities.User;
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
}
