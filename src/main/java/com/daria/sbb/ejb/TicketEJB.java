package com.daria.sbb.ejb;

/**
 * Created by Дарья on 12.04.2015.
 */
        import com.daria.sbb.jpa.entities.Ticket;
        import com.daria.sbb.jpa.entities.TrainDeparture;
        import com.daria.sbb.jpa.entities.User;
        import org.apache.log4j.Logger;

        import javax.ejb.Stateless;
        import javax.persistence.EntityManager;
        import javax.persistence.PersistenceContext;
        import javax.persistence.TypedQuery;
        import java.util.List;


@Stateless
public class TicketEJB {

    private static final Logger log = Logger.getLogger(TicketEJB.class.getName());

    @PersistenceContext(unitName = "SBB_PU")
    private EntityManager entityManager;

    public List<Ticket> getAll() {
        TypedQuery<Ticket> query = entityManager.createNamedQuery("Ticket.getAll", Ticket.class);
        List<Ticket> list = query.getResultList();
        return list;
    }

    public Ticket addNew(Ticket station) {
        entityManager.merge(station);
        return station;
    }

    public boolean isExist(User user, TrainDeparture trainDeparture) {
        log.info(String.format("Start check ticket with user: %s and departure: %s", user.getLogin(), trainDeparture.toString()));
        TypedQuery<Ticket> query = entityManager.createNamedQuery("Ticket.findByUserAndTrain", Ticket.class);
        query.setParameter("user", user);
        query.setParameter("trainDeparture", trainDeparture);
        List<Ticket> list = query.getResultList();
        if (list.isEmpty()){
            log.info(String.format("No such ticket"));
            return false;
        } else {
            log.info(String.format("Such ticket already exist in database"));
            return true;
        }
    }

    public List<Ticket> findByDeparture(TrainDeparture trainDeparture) {
        log.info("Start check train with name: " + trainDeparture.toString());
        TypedQuery<Ticket> query = entityManager.createNamedQuery("Ticket.findByDeparture", Ticket.class);
        query.setParameter("trainDeparture", trainDeparture);
        List<Ticket> list = query.getResultList();
        if (!list.isEmpty()){
            log.info(String.format("Ticket with name like %s found", trainDeparture.toString()));
        } else {
            log.info(String.format("No tickets with departure like %s", trainDeparture.toString()));
        }
        return list;
    }
}
