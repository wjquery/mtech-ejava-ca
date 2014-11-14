package sg.edu.nus.iss.ems.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import sg.edu.nus.iss.ems.entity.Role;
import sg.edu.nus.iss.ems.exception.EmsException;
import sg.edu.nus.iss.ems.service.UserMgmtService;

@FacesConverter("roleConverter")
public class RoleConverter implements Converter {
 
    @Inject
    private UserMgmtService userBean;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                return userBean.loadRole(Integer.parseInt(value));
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
            return String.valueOf(((Role) object).getId());
        }
        else {
            return null;
        }
    }  
}