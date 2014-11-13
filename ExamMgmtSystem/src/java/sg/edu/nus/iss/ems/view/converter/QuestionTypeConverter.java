package sg.edu.nus.iss.ems.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import sg.edu.nus.iss.ems.entity.QuestionType;
import sg.edu.nus.iss.ems.exception.EmsException;
import sg.edu.nus.iss.ems.service.QuestionMgmtService;

@FacesConverter("questionTypeConverter")
public class QuestionTypeConverter implements Converter {
 
    @Inject
    private QuestionMgmtService questionBean;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                return questionBean.loadQuestionType(Integer.parseInt(value));
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
            return String.valueOf(((QuestionType) object).getId());
        }
        else {
            return null;
        }
    }  
}
