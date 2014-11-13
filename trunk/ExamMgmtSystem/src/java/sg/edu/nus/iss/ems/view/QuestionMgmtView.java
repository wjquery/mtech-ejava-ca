package sg.edu.nus.iss.ems.view;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sg.edu.nus.iss.ems.entity.Module;
import sg.edu.nus.iss.ems.entity.Question;
import sg.edu.nus.iss.ems.entity.QuestionType;
import sg.edu.nus.iss.ems.service.QuestionMgmtService;
import sg.edu.nus.iss.ems.util.JsfUtil;

@SessionScoped
@Named
public class QuestionMgmtView implements Serializable {
    
    @Inject
    private LoginView loginView;
    
    @EJB
    private QuestionMgmtService questionBean;
    
    private List<Question> questions;
    private Question selectedQn;
    
    private Module module;
    private int offset = 0;
    private int size = 15;

    // setters & getters
    public List<Question> getQuestions() {
        //if (questions == null)
            questions = questionBean.findQuestionsByModule(module==null?"":module.getCode(), offset, size, true);
        return questions;
    }

    public Question getSelectedQn() {
        return selectedQn;
    }

    public void setSelectedQn(Question selectedQn) {
        this.selectedQn = selectedQn;
    }
    
    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
    
    // CRUD methods
    public Question prepareCreate() {
        selectedQn = new Question();
        selectedQn.setCreatedBy(loginView.getLoginUser());
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