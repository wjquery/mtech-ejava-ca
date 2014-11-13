package sg.edu.nus.iss.ems.service.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import sg.edu.nus.iss.ems.entity.Question;
import sg.edu.nus.iss.ems.entity.QuestionType;
import sg.edu.nus.iss.ems.service.QuestionMgtService;

@Stateless
public class QuestionBean extends GenericDataAccessService<Question> implements QuestionMgtService {
    
    private static final String FIND_BY_MODULE = 
            "select q from Question q where q.module.code = :moduleCode";
    private static final String FIND_ACTIVE_BY_MODULE = 
            "select q from Question q where q.module.code = :moduleCode and q.status = 1";
    private static final String FIND_BY_MODULE_AND_QID = 
            "select q from Question q where q.module.code = :moduleCode and q.qid = :qid";
    private static final String FIND_ALL_QUESTION_TYPES =
            "QuestionType.findAll";
    
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
                .setMaxResults(offset + size);
        return q.getResultList();
    }
    
    @Override
    public void create(Question question) {
        int qid = seqGenerator.next(question.getModule().getCode());
        question.setQid(qid);
        question.setStatus(1);
        super.create(question);
    }
    
    @Override
    public Question update(Question question) {
        Question prevQn = em.createQuery(FIND_BY_MODULE_AND_QID, Question.class)
                .setParameter("moduleCode", question.getModule().getCode())
                .setParameter("qid", question.getQid())
                .getSingleResult();
        prevQn.setStatus(0);
        super.update(question);
        
        question.setStatus(1);
        super.create(question);
        return question;
    }
    
    @Override
    public List<QuestionType> findAllQuestionTypes() {
        return em.createNamedQuery(FIND_ALL_QUESTION_TYPES, QuestionType.class).getResultList();
    }
}
