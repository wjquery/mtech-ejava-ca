package sg.edu.nus.iss.ems.view;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sg.edu.nus.iss.ems.entity.Question;
import sg.edu.nus.iss.ems.service.QuestionMgtService;

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

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
    
    public List<Question> findQuestionsByModules() {
        return questionBean.findQuestionsByModule(module, offset, size, true);
    }
    
    public void findQuestion(int id) {
        selectedQn = questionBean.load(id);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Question getSelectedQn() {
        return selectedQn;
    }

    public void setSelectedQn(Question selectedQn) {
        this.selectedQn = selectedQn;
    }
    
}
