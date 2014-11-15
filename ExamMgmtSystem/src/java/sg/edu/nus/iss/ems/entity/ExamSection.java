package sg.edu.nus.iss.ems.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "exam_section")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamSection.findAll", query = "SELECT e FROM ExamSection e"),
    @NamedQuery(name = "ExamSection.findById", query = "SELECT e FROM ExamSection e WHERE e.id = :id"),
    @NamedQuery(name = "ExamSection.findByName", query = "SELECT e FROM ExamSection e WHERE e.name = :name"),
    @NamedQuery(name = "ExamSection.findByTotalMarks", query = "SELECT e FROM ExamSection e WHERE e.totalMarks = :totalMarks"),
    @NamedQuery(name = "ExamSection.findByType", query = "SELECT e FROM ExamSection e WHERE e.type = :type")})
public class ExamSection implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    
    @Size(max = 255)
    @Column(name = "Name")
    private String name;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TotalMarks")
    private Double totalMarks;
    
    @Size(max = 1)
    @Column(name = "Type")
    private String type;
    
    @JoinColumn(name = "TemplateId", referencedColumnName = "Id")
    @ManyToOne
    private ExamTemplate template;

    @JoinTable(name = "section_question", 
            joinColumns = @JoinColumn(name = "SectionId", referencedColumnName = "Id"),
            inverseJoinColumns = @JoinColumn(name = "QuestionId", referencedColumnName = "Id"))
    @ManyToMany
    private List<Question> questions;
    
    @JoinTable(name = "section_tag", 
            joinColumns = @JoinColumn(name = "SectionId", referencedColumnName = "Id"),
            inverseJoinColumns = @JoinColumn(name = "TagId", referencedColumnName = "Id"))
    @ManyToMany
    private List<SubjectTag> tags;
    
    public ExamSection() {
    }

    public ExamSection(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(Double totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ExamTemplate getTemplate() {
        return template;
    }

    public void setTemplate(ExamTemplate template) {
        this.template = template;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<SubjectTag> getTags() {
        return tags;
    }

    public void setTags(List<SubjectTag> tags) {
        this.tags = tags;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamSection)) {
            return false;
        }
        ExamSection other = (ExamSection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sg.edu.nus.iss.ems.entity.ExamSection[ id=" + id + " ]";
    }
    
}
