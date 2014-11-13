package sg.edu.nus.iss.ems.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import sg.edu.nus.iss.ems.entity.Module;
import sg.edu.nus.iss.ems.exception.EmsException;
import sg.edu.nus.iss.ems.service.ModuleMgmtService;

@FacesConverter("moduleConverter")
public class ModuleConverter implements Converter {
 
    @Inject
    private ModuleMgmtService moduleBean;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                return moduleBean.load(value);
            } catch(NumberFormatException e) {
                throw new EmsException("Conversion Error", e);
            }
        }
        else {
            return null;
        }
    }
 
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Module) object).getCode());
        }
        else {
            return null;
        }
    }  
}