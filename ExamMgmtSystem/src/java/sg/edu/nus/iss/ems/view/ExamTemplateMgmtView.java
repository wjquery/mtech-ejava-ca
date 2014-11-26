package sg.edu.nus.iss.ems.view;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sg.edu.nus.iss.ems.service.impl.ExamTemplateBean;

@SessionScoped
@Named
public class ExamTemplateMgmtView implements Serializable {
    
    @Inject
    private LoginView loginView;
    
    @Inject 
    private ExamTemplateBean bean;
    
    
    
}
