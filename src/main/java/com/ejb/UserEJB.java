package com.ejb;

import com.jpa.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Дарья on 31.03.2015.
 */



@Stateless
public class UserEJB {
    @PersistenceContext(unitName = "SBB_PU")
    private EntityManager entityManager;

    public List<User> findUsers() {
        TypedQuery<User> query = entityManager.createNamedQuery("User.getAll", User.class);
        List<User> list = query.getResultList();
        return list;
    }

    public List<User> findUsersByLogin(String login) {
        TypedQuery<User> query = entityManager.createNamedQuery("User.findByName", User.class);
        query.setParameter("login", login);
        List<User> list = query.getResultList();
        return list;
    }

    public User addNew(User user) {
        entityManager.persist(user);
        return user;
    }

    public boolean login(String login, String password) {
        try {
            TypedQuery<User> query = entityManager.createNamedQuery("User.findByLoginAndPassword", User.class);
            query.setParameter("login", login);
            query.setParameter("password", password);
            List<User> list = query.getResultList();
            if (!list.isEmpty()){
                System.out.println(list.get(0).getLogin());
                return true;
            } else {
                System.out.println("List is empty");
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        }
    }
}
