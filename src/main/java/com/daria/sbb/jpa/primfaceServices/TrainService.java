package com.daria.sbb.jpa.primfaceServices;

/**
 * Created by Дарья on 10.04.2015.
 */

        import com.daria.sbb.ejb.TrainEJB;
        import com.daria.sbb.jpa.entities.Train;

        import java.util.List;
        import javax.annotation.PostConstruct;
        import javax.ejb.EJB;
        import javax.faces.bean.ApplicationScoped;
        import javax.faces.bean.ManagedBean;


@ManagedBean(name="trainService", eager = true)
@ApplicationScoped
public class TrainService {

    private List<Train> trains;

    @EJB
    TrainEJB stEJB = new TrainEJB();

    @PostConstruct
    public void init() {

    }

    public List<Train> getStations() {
        return trains;
    }

    public Train getTrainByID(int i) {
        trains = stEJB.getAll();
        for (Train train : trains) {
            if(train.getId() == i){
                return train;
            }
        }
        return null;
    }
}
