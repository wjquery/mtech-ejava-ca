package sg.edu.nus.iss.ems.service;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import sg.edu.nus.iss.ems.entity.ExamTemplate;

@Local
public interface ExamTemplateMgmtService {
    public List<ExamTemplate> findAll();
    public ExamTemplate load(Serializable primaryKey);
    public void create(ExamTemplate module);
    public ExamTemplate update(ExamTemplate examTemplate);
    public void delete(ExamTemplate examTemplate);
    
    public List<ExamTemplate> findTemplatesByModule(String moduleCode);
}
