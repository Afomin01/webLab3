package Utils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("RuDoubleConverter")
public class RUDoubleConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try{
            Double result = Double.parseDouble(value.replace(',','.'));
            return result;
        }catch (Exception e){
            throw new ConverterException("Invalid RU double");
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try{
            return ((Double) value).toString().replace(',','.');
        }catch (Exception e){
            throw new ConverterException("Invalid RU double");
        }
    }
}
