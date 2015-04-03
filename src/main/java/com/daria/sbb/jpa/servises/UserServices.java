package com.daria.sbb.jpa.servises;

import com.daria.sbb.jpa.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Дарья on 08.03.2015.
 */
public class UserServices {

    @PersistenceContext(unitName="SBB_PU")
    public EntityManager em = Persistence.createEntityManagerFactory("SBB_PU").createEntityManager();

    public User add(User user){
        em.getTransaction().begin();
        User userFromDB = em.merge(user);
        em.getTransaction().commit();
        return userFromDB;
    }

    public void delete(long id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public User get(long id){
        return em.find(User.class, id);
    }

    public void update(User user){
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    public List<User> getAll(){
        TypedQuery<User> namedQuery = em.createNamedQuery("User.getAll", User.class);
        return namedQuery.getResultList();
    }

}
