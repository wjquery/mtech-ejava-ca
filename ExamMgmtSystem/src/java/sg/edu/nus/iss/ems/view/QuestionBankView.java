package sg.edu.nus.iss.ems.view;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import sg.edu.nus.iss.ems.entity.Question;
import sg.edu.nus.iss.ems.service.impl.QuestionBean;

@RequestScoped
@Named
public class QuestionBankView {
    
    @EJB
    private QuestionBean questionBean;
    
    private String module;
    private int offset = 0;
    private int size = 15;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
    
    public List<Question> findQuestionsByModules() {
        return questionBean.findQuestionsByModule(module, offset, size);
    }
    
    
}
