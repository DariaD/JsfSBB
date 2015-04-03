package com.daria.sbb.ejb;

import com.daria.sbb.jpa.entities.User;
import org.apache.log4j.Logger;

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

    private static final Logger log = Logger.getLogger(UserEJB.class.getName());

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

    public boolean isExist(String login) {
            log.info("Start check user with login: " + login);
            TypedQuery<User> query = entityManager.createNamedQuery("User.findByName", User.class);
            query.setParameter("login", login);
            List<User> list = query.getResultList();
            if (list.isEmpty()){
                log.info(String.format("No user with login like %s", login));
                return false;
            } else {
                log.info(String.format("User with login %s exist in database", login));
                return true;
            }
    }

    public User login(String login, String password) {
        try {
            log.info("Start check user with login: " + login);
            TypedQuery<User> query = entityManager.createNamedQuery("User.findByLoginAndPassword", User.class);
            query.setParameter("login", login);
            query.setParameter("password", password);
            List<User> list = query.getResultList();
            if (!list.isEmpty()){
                User user = list.get(0);
                log.info(String.format("Find such user: real name - %s %s", user.getFirstName(), user.getSecondName()));
                return user;
            } else {
                log.info("Not find such user in database");
                return null;
            }
        } catch (Exception ex) {
            log.info(String.format("Error in login() -->" + ex.getMessage()));
            return null;
        }
    }
}
