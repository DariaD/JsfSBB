package com.daria.utils.validators;

/**
 * Created by Дарья on 07.04.2015.
 */
        import com.daria.sbb.ejb.TrainEJB;
        import org.apache.log4j.Logger;

        import javax.ejb.EJB;
        import javax.faces.application.FacesMessage;
        import javax.faces.bean.ManagedBean;
        import javax.faces.bean.SessionScoped;
        import javax.faces.component.UIComponent;
        import javax.faces.context.FacesContext;
        import javax.faces.validator.FacesValidator;
        import javax.faces.validator.Validator;
        import javax.faces.validator.ValidatorException;

/**
 * Created by Дарья on 07.04.2015.
 */

@ManagedBean
@SessionScoped
@FacesValidator("TrainValidator")
public class TrainValidation implements Validator {

    private static final Logger log = Logger.getLogger(TrainValidation.class.getName());

    @EJB
    private TrainEJB trainEJB = new TrainEJB();

    private String trainName;

    public TrainValidation(){}

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        trainName = value.toString();
        log.info("Input train name: " + trainName);
        if(trainEJB.isExist(trainName)|| trainName.trim().length()==0){
            log.info("Validation failed!");
            FacesMessage msg = new FacesMessage("Such train already exist", "Please choose another train name.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        } else {
            log.info("Validation ok!");
        }

    }
}
