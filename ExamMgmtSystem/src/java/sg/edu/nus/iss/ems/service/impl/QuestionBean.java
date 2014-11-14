package sg.edu.nus.iss.ems.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import sg.edu.nus.iss.ems.entity.Question;
import sg.edu.nus.iss.ems.entity.QuestionType;
import sg.edu.nus.iss.ems.entity.SubjectTag;
import sg.edu.nus.iss.ems.service.QuestionMgmtService;

@Stateless
public class QuestionBean extends GenericDataAccessService<Question> implements QuestionMgmtService {
    
    private static final String FIND_BY_MODULE = 
            "select q from Question q where q.module.code = :moduleCode";
    private static final String FIND_ACTIVE_BY_MODULE = 
            "select q from Question q where q.module.code = :moduleCode and q.status = 1";
    private static final String FIND_BY_MODULE_AND_QID = 
            "select q from Question q where q.module.code = :moduleCode and q.qid = :qid and q.status = 1";
    private static final String FIND_BY_MODULE_AND_TAG = 
            "select q from Question q join q.subjectTags qt where q.module.code = :moduleCode and qt.tag in (:tags) and q.status = 1";
    private static final String FIND_ALL_QUESTION_TYPES =
            "QuestionType.findAll";
    private static final String FIND_ALL_TAGS =
            "SubjectTag.findAll";
    
    @EJB
    private QuestionSeqGenerator seqGenerator;
    
    @Override
    public List<Question> findQuestionsByModule(String moduleCode, int offset, int size, boolean activeOnly) {
        String sql;
        if (activeOnly) sql = FIND_ACTIVE_BY_MODULE;
        else sql = FIND_BY_MODULE;
        TypedQuery<Question> q = em.createQuery(sql, Question.class)
                .setParameter("moduleCode", moduleCode)
                .setFirstResult(offset)
                .setMaxResults(size);
        return q.getResultList();
    }
    
    // find potential questions for sub parts
    @Override
    public List<Question> findQuestionsByModuleAndTag(String moduleCode, List<SubjectTag> tags) {
        if (tags != null && !tags.isEmpty()) {
            String tagStr = "";
            for (SubjectTag tag : tags) {
                tagStr += tag.getTag() + ",";
            }
            if (tagStr.length() > 0) {
                tagStr = tagStr.substring(0, tagStr.length() -1);
            }
            TypedQuery<Question> q = em.createQuery(FIND_BY_MODULE_AND_TAG, Question.class)
                    .setParameter("moduleCode", moduleCode)
                    .setParameter("tags", tagStr);
            return q.getResultList();
        } else {
            TypedQuery<Question> q = em.createQuery(FIND_ACTIVE_BY_MODULE, Question.class)
                    .setParameter("moduleCode", moduleCode);
            return q.getResultList();
        }
    }
    
    @Override
    public void create(Question question) {
        int qid = seqGenerator.next(question.getModule().getCode());
        question.setQid(qid);
        question.getModule().setQuestionCount(question.getModule().getQuestionCount() + 1);
        question.setStatus(1);
        question.setVersion(1);
        question.setCreatedOn(new Date());
        // clear choices if type is not MCQ
        if (question.getQuestionType().getId() > 2)
            question.setChoices(null);
        // clear parts if type is not Multipart
        if (question.getQuestionType().getId() != 4)
            question.setParts(null);
        super.create(question);
    }
    
    @Override
    public Question update(Question question) {
        Question prevQn = em.createQuery(FIND_BY_MODULE_AND_QID, Question.class)
                .setParameter("moduleCode", question.getModule().getCode())
                .setParameter("qid", question.getQid())
                .getSingleResult();
        prevQn.setStatus(0);
        super.update(prevQn);
        
        question.setVersion(question.getVersion() + 1);
        question.setStatus(1);
        question.setCreatedOn(new Date());
        // clear choices if type is not MCQ
        if (question.getQuestionType().getId() > 2)
            question.setChoices(null);
        // clear parts if type is not Multipart
        if (question.getQuestionType().getId() != 4)
            question.setParts(null);
        super.create(question);
        return question;
    }
    
    @Override
    public List<QuestionType> findAllQuestionTypes() {
        return em.createNamedQuery(FIND_ALL_QUESTION_TYPES, QuestionType.class).getResultList();
    }
    
    @Override
    public QuestionType loadQuestionType(Serializable primaryKey) {
        return em.find(QuestionType.class, primaryKey);
    }
    
    @Override
    public List<SubjectTag> findAllSubjectTags() {
        return em.createNamedQuery(FIND_ALL_TAGS, SubjectTag.class).getResultList();
    }
    
    @Override
    public SubjectTag loadSubjectTag(Serializable primaryKey) {
        return em.find(SubjectTag.class, primaryKey);
    }
}
