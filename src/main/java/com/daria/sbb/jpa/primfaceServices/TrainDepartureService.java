package com.daria.sbb.jpa.primfaceServices;

/**
 * Created by Дарья on 11.04.2015.
 */
        import java.util.List;
        import javax.annotation.PostConstruct;
        import javax.ejb.EJB;
        import javax.faces.bean.ApplicationScoped;
        import javax.faces.bean.ManagedBean;

        import com.daria.sbb.ejb.DepartureEJB;
        import com.daria.sbb.ejb.StationEJB;
        import com.daria.sbb.jpa.entities.Station;
        import com.daria.sbb.jpa.entities.TrainDeparture;

@ManagedBean(name="trainDepartureService", eager = true)
@ApplicationScoped
public class TrainDepartureService {

    private List<TrainDeparture> trains;

    @EJB
    DepartureEJB stEJB = new DepartureEJB();

    @PostConstruct
    public void init() {
        trains = stEJB.getAll();
    }

    public List<TrainDeparture> getStations() {
        return trains;
    }

    public TrainDeparture getTrainDepartureByID(int i) {
        for (TrainDeparture train : trains) {
            if(train.getIdTrainDeparture() == i){
                return train;
            }
        }
        return null;
    }
}