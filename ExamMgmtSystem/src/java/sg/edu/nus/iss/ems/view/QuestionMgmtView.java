package sg.edu.nus.iss.ems.view;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sg.edu.nus.iss.ems.entity.McqChoice;
import sg.edu.nus.iss.ems.entity.Module;
import sg.edu.nus.iss.ems.entity.Question;
import sg.edu.nus.iss.ems.entity.QuestionPart;
import sg.edu.nus.iss.ems.entity.QuestionType;
import sg.edu.nus.iss.ems.entity.SubjectTag;
import sg.edu.nus.iss.ems.service.QuestionMgmtService;
import sg.edu.nus.iss.ems.util.JsfUtil;

@SessionScoped
@Named
public class QuestionMgmtView implements Serializable {
    
    @Inject
    private LoginView loginView;
    
    @EJB
    private QuestionMgmtService questionBean;
    
    private int offset = 0;
    private int size = 1000;
    private List<Question> questions;
    private Question selectedQn;
    
    private Module module;
    
    private McqChoice choice;
    private char choiceSeq;
    
    private QuestionPart part;
    private char partSeq;

    // setters & getters
    public List<Question> getQuestions() {
        //if (questions == null)
        if (module == null && loginView.getLoginUser() != null 
                && !loginView.getLoginUser().getModules().isEmpty()) {
            module = loginView.getLoginUser().getModules().get(0);
            
        }
        questions = questionBean.findQuestionsByModule(module.getCode(), offset, size, true);
        // sort choices and parts in ABCD order
        for (Question q : questions) {
            Collections.sort(q.getChoices(), new Comparator<McqChoice>() {
                @Override
                public int compare(McqChoice o1, McqChoice o2) {
                    return o1.getChoice().charAt(0) - o2.getChoice().charAt(0);
                }
            });
            Collections.sort(q.getParts(), new Comparator<QuestionPart>() {
                @Override
                public int compare(QuestionPart o1, QuestionPart o2) {
                    return o1.getPartName().charAt(0) - o2.getPartName().charAt(0);
                }
            });
        }
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

    public McqChoice getChoice() {
        return choice;
    }

    public void setChoice(McqChoice choice) {
        this.choice = choice;
    }

    public QuestionPart getPart() {
        return part;
    }

    public void setPart(QuestionPart part) {
        this.part = part;
    }

    
    // CRUD methods
    public Question prepareCreate() {
        selectedQn = new Question();
        selectedQn.setMark(0.0);
        selectedQn.setCreatedBy(loginView.getLoginUser());
        selectedQn.setChoices(new LinkedList<McqChoice>());
        selectedQn.setQuestionType(new QuestionType(1));
        selectedQn.setSubjectTags(new LinkedList<SubjectTag>());
        selectedQn.setParts(new LinkedList<QuestionPart>());
        
        choice = new McqChoice();
        choiceSeq = 'A';
        choice.setChoice(String.valueOf(choiceSeq));
        
        part = new QuestionPart();
        partSeq = 'A';
        part.setPartName(String.valueOf(partSeq));
        return selectedQn;
    }
    
    public void create() {
        if (selectedQn.getParts() != null && !selectedQn.getParts().isEmpty()) {
            double sum = 0;
            List<QuestionPart> partList = selectedQn.getParts();
            for (QuestionPart p : partList)
                sum += p.getQuestion().getMark();
            selectedQn.setMark(sum);
        }
        questionBean.create(selectedQn);
        if (!JsfUtil.isValidationFailed()) {
            questions = null;    // Invalidate list of questions to trigger re-query.
        }
    }
    
    public void prepareEdit() {
        List<McqChoice> choices = selectedQn.getChoices();
        if (choices != null && !choices.isEmpty()) {
            int count = selectedQn.getChoices().size();
            String lastChoice = selectedQn.getChoices().get(count - 1).getChoice();
            choiceSeq = (char)(lastChoice.charAt(0) + 1);
            choice = new McqChoice();
            choice.setChoice(String.valueOf(choiceSeq));
        } else {
            selectedQn.setChoices(new LinkedList<McqChoice>());
            choice = new McqChoice();
            choiceSeq = 'A';
            choice.setChoice(String.valueOf(choiceSeq));
        }
        
        List<QuestionPart> parts = selectedQn.getParts();
        if (parts != null && !parts.isEmpty()) {
            int count = selectedQn.getParts().size();
            String lastPart = selectedQn.getParts().get(count - 1).getPartName();
            partSeq = (char)(lastPart.charAt(0) + 1);
            part = new QuestionPart();
            part.setPartName(String.valueOf(partSeq));
        } else {
            selectedQn.setParts(new LinkedList<QuestionPart>());
            part = new QuestionPart();
            partSeq = 'A';
            part.setPartName(String.valueOf(partSeq));
        }
    }
    
    public void update() {
        questionBean.update(selectedQn);
    }
    
    // listeners
    public List<QuestionType> findAllQuestionTypes() {
        return questionBean.findAllQuestionTypes();
    }
    
    public List<SubjectTag> findAllSubjectTags() {
        return questionBean.findAllSubjectTags();
    }
    
    public List<Question> findSubQuestions() {
        return questionBean.findQuestionsByModuleAndTag(module.getCode(), selectedQn.getSubjectTags());
    }
    
    public void addChoice() {
        selectedQn.getChoices().add(copy(choice));
        
        // reset choice
        choice = new McqChoice();
        choiceSeq = (char) (choiceSeq + 1);
        choice.setChoice(String.valueOf(choiceSeq));
    }
    
    public void addPart() {
        // update total marks of parent question
        selectedQn.getParts().add(copy(part));
        double sum = 0;
        List<QuestionPart> partList = selectedQn.getParts();
        for (QuestionPart p : partList)
            sum += p.getQuestion().getMark();
        selectedQn.setMark(sum);
        
        // reset part
        part = new QuestionPart();
        partSeq = (char) (partSeq + 1);
        part.setPartName(String.valueOf(partSeq));
    }
    
    // helper methods
    private McqChoice copy(McqChoice choice) {
        McqChoice copied = new McqChoice();
        copied.setQuestion(selectedQn);
        copied.setChoice(choice.getChoice());
        copied.setChoiceText(choice.getChoiceText());
        return copied;
    }
    
    private QuestionPart copy(QuestionPart part) {
        QuestionPart copied = new QuestionPart();
        copied.setParent(selectedQn);
        copied.setPartName(part.getPartName());
        copied.setQuestion(part.getQuestion());
        return copied;
    }
}
