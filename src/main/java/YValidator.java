import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("YValueValidator")
public class YValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        try{
            double num = Double.parseDouble(value.toString());
            if(num<=-5 || num>=5) throw new ValidatorException(new FacesMessage("Invalid value"));
        }catch (Exception e){
            throw new ValidatorException(new FacesMessage("Invalid value"));
        }
    }
}
