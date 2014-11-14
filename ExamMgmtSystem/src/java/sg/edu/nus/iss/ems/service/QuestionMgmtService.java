package sg.edu.nus.iss.ems.service;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import sg.edu.nus.iss.ems.entity.Question;
import sg.edu.nus.iss.ems.entity.QuestionType;
import sg.edu.nus.iss.ems.entity.SubjectTag;

@Local
public interface QuestionMgmtService {
    public List<Question> findQuestionsByModule(String moduleCode, int offset, int size, boolean activeOnly);
    public Question load(Serializable primaryKey);
    public void create(Question question);
    public Question update(Question question);
    
    public List<QuestionType> findAllQuestionTypes();
    public QuestionType loadQuestionType(Serializable primaryKey);
    public List<SubjectTag> findAllSubjectTags();
    public SubjectTag loadSubjectTag(Serializable primaryKey);
}
