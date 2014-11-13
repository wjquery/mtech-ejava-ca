package sg.edu.nus.iss.ems.view;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sg.edu.nus.iss.ems.entity.Question;
import sg.edu.nus.iss.ems.entity.QuestionType;
import sg.edu.nus.iss.ems.service.QuestionMgtService;
import sg.edu.nus.iss.ems.util.JsfUtil;

@ViewScoped
@Named
public class QuestionMgmtView implements Serializable {
    
    @EJB
    private QuestionMgtService questionBean;
    
    private List<Question> questions;
    private Question selectedQn;
    
    private String module;
    private int offset = 0;
    private int size = 15;

    // setters & getters
    public List<Question> getQuestions() {
        if (questions == null)
            questions = questionBean.findQuestionsByModule(module, offset, size, true);
        return questions;
    }

    public Question getSelectedQn() {
        return selectedQn;
    }

    public void setSelectedQn(Question selectedQn) {
        this.selectedQn = selectedQn;
    }
    
    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
    
    // CRUD methods
    public Question prepareCreate() {
        selectedQn = new Question();
        
        return selectedQn;
    }
    
    public void create() {
        questionBean.create(selectedQn);
        if (!JsfUtil.isValidationFailed()) {
            questions = null;    // Invalidate list of questions to trigger re-query.
        }
    }
    
    // helper methods
    public List<QuestionType> findAllQuestionTypes() {
        return questionBean.findAllQuestionTypes();
    }
    
}
