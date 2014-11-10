package sg.edu.nus.iss.ems.service.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import sg.edu.nus.iss.ems.entity.Question;

@Stateless
public class QuestionBean extends GenericDataAccessService<Question> {
    
    private static final String FIND_BY_MODULE = 
            "select q from Question q where q.moduleCode.code = :moduleCode";
    
    @EJB
    private QuestionSeqGenerator seqGenerator;
    
    public List<Question> findQuestionsByModule(String moduleCode, int offset, int size) {
        TypedQuery<Question> q = em.createQuery(FIND_BY_MODULE, Question.class)
                .setParameter("moduleCode", moduleCode)
                .setFirstResult(offset)
                .setMaxResults(offset + size);
        return q.getResultList();
    }
    
    @Override
    public void create(Question question) {
        int qid = seqGenerator.next(question.getModule().getCode());
        question.setQid(qid);
        super.create(question);
    }
    
}
