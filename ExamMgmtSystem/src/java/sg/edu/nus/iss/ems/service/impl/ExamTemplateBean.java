package sg.edu.nus.iss.ems.service.impl;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import sg.edu.nus.iss.ems.entity.ExamTemplate;
import sg.edu.nus.iss.ems.service.ExamTemplateMgmtService;

@Stateless
public class ExamTemplateBean extends GenericDataAccessService<ExamTemplate> implements ExamTemplateMgmtService {
    
    private static final String FIND_BY_MODULE = 
            "select t from ExamTemplate t where t.module.code = :moduleCode";

    @Override
    public List<ExamTemplate> findTemplatesByModule(String moduleCode) {
        TypedQuery<ExamTemplate> q = em.createQuery(FIND_BY_MODULE, ExamTemplate.class)
                .setParameter("moduleCode", moduleCode);
        return q.getResultList();
    }
    
    
    
}
