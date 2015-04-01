package com.jpa.servises;

import com.jpa.entities.Station;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Дарья on 05.03.2015.
 */
public class StationServices {

    @PersistenceContext(unitName="SBB_PU")
    public EntityManager em = Persistence.createEntityManagerFactory("SBB_PU").createEntityManager();

        public Station add(Station station){
            em.getTransaction().begin();
            Station stationFromDB = em.merge(station);
            em.getTransaction().commit();
            return stationFromDB;
        }

        public void delete(long id){
            em.getTransaction().begin();
            em.remove(get(id));
            em.getTransaction().commit();
        }

        public Station get(long id){
            return em.find(Station.class, id);
        }

        public void update(Station station){
            em.getTransaction().begin();
            em.merge(station);
            em.getTransaction().commit();
        }

        public List<Station> getAll(){
            TypedQuery<Station> namedQuery = em.createNamedQuery("Station.getAll", Station.class);
            return namedQuery.getResultList();
        }

    }
