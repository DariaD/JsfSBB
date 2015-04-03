package com.daria.sbb.jpa.servises;

import com.daria.sbb.jpa.entities.TestEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Дарья on 29.03.2015.
 */
public class TestServises {

    @PersistenceContext(unitName="SBB_PU")
    public EntityManager em = Persistence.createEntityManagerFactory("SBB_PU").createEntityManager();

    public TestEntity add(TestEntity station){
        em.getTransaction().begin();
        TestEntity stationFromDB = em.merge(station);
        em.getTransaction().commit();
        return stationFromDB;
    }

    public TestEntity get(long id){
        return em.find(TestEntity.class, id);
    }

    public void update(TestEntity station){
        em.getTransaction().begin();
        em.merge(station);
        em.getTransaction().commit();
    }

    public List<TestEntity> getAll(){
        TypedQuery<TestEntity> namedQuery = em.createNamedQuery("TestEntity.getAll", TestEntity.class);
        return namedQuery.getResultList();
    }
}
