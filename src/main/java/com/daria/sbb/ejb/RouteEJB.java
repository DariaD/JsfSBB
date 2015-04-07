package com.daria.sbb.ejb;

import com.daria.sbb.jpa.entities.Route;
import com.daria.sbb.jpa.entities.Station;
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
public class RouteEJB {

    private static final Logger log = Logger.getLogger(RouteEJB.class.getName());

    @PersistenceContext(unitName = "SBB_PU")
    private EntityManager entityManager;

    public List<Route> getAll() {
        TypedQuery<Route> query = entityManager.createNamedQuery("Route.getAll", Route.class);
        List<Route> list = query.getResultList();
        return list;
    }


    public Route addNew(Route route) {
        //entityManager.persist(route);
        entityManager.merge(route);
        return route;
    }

    public boolean isExist(Station stationOne, Station stationTwo) {

//        public boolean isExist(String stationNameOne, String stationNameTwo) {
        log.info(String.format("Start check station with name: %s and %s ", stationOne, stationTwo));

//        StationEJB stationEJB = new StationEJB();
//        Station stationOne = stationEJB.findByName(stationNameOne);
//        Station stationTwo = stationEJB.findByName(stationNameTwo);
//
        TypedQuery<Route> queryOne = entityManager.createNamedQuery("Route.findByNames", Route.class);
        queryOne.setParameter("stationOne", stationOne);
        queryOne.setParameter("stationTwo", stationTwo);
        List<Route> listOne = queryOne.getResultList();

        TypedQuery<Route> queryTwo = entityManager.createNamedQuery("Route.findByNames", Route.class);
        queryTwo.setParameter("stationOne", stationTwo);
        queryTwo.setParameter("stationTwo", stationOne);
        List<Route> listTwo = queryTwo.getResultList();

        if (listOne.isEmpty()&&listTwo.isEmpty()){
            log.info(String.format("No routes between %s and %s", stationOne, stationTwo));
            return false;
        } else {
            log.info(String.format("routes between %s and %s is exist", stationOne, stationTwo));
            return true;
        }
    }

}
