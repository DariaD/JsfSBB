package com.daria.sbb.ejb;

import com.daria.sbb.jpa.entities.TestEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Дарья on 29.03.2015.
 */

@Stateless
public class TestEJB {
    @PersistenceContext(unitName = "SBB_PU")
    private EntityManager entityManager;

    public List<TestEntity> findTestEntity(){
        TypedQuery<TestEntity> query = entityManager.createNamedQuery("TestEntity.getAll", TestEntity.class);
        List<TestEntity> list = query.getResultList();
        return list;
    }

    public TestEntity addNew(TestEntity employee) {
        entityManager.persist(employee);
        return employee;
    }
}



