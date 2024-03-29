/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ems.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wenyan
 */
@Entity
@Table(name = "question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q WHERE q.status = 1"),
    @NamedQuery(name = "Question.findById", query = "SELECT q FROM Question q WHERE q.id = :id AND q.status = 1"),
    @NamedQuery(name = "Question.findByQid", query = "SELECT q FROM Question q WHERE q.qid = :qid AND q.status = 1"),
    @NamedQuery(name = "Question.findByVersion", query = "SELECT q FROM Question q WHERE q.version = :version AND q.status = 1"),
    @NamedQuery(name = "Question.findByCreatedOn", query = "SELECT q FROM Question q WHERE q.createdOn = :createdOn AND q.status = 1"),
    @NamedQuery(name = "Question.findByMark", query = "SELECT q FROM Question q WHERE q.mark = :mark AND q.status = 1")})
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "QID")
    private Integer qid;
    @Column(name = "Version")
    private Integer version;
    
    @Column(name = "Created_On")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Mark")
    private Double mark;
    
    @Lob
    @Size(max = 65535)
    @Column(name = "Question_Text")
    private String questionText;
    
    @Column(name = "Status")
    private Integer status;
    
    @JoinColumn(name = "Module_Code", referencedColumnName = "Code")
    @ManyToOne
    @JsonManagedReference
    private Module module;
    
    @JoinColumn(name = "Created_By", referencedColumnName = "Id")
    @ManyToOne
    @JsonManagedReference
    private User createdBy;

    @OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<McqChoice> choices;
    
    @JoinColumn(name = "Type", referencedColumnName = "Id")
    @ManyToOne
    @JsonManagedReference
    private QuestionType questionType;
    
    @ManyToMany
    @JoinTable(name = "question_tag", 
            joinColumns = @JoinColumn(name = "Question_Id", referencedColumnName = "Id"),
            inverseJoinColumns = @JoinColumn(name = "Tag_Id", referencedColumnName = "Id"))
    @JsonManagedReference
    private List<SubjectTag> subjectTags;
    
    @OneToMany(mappedBy = "parent", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<QuestionPart> parts;
    
    @ManyToMany(mappedBy = "questions")
    private List<ExamSection> sections;
    
    public Question() {
    }

    public Question(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public List<McqChoice> getChoices() {
        return choices;
    }

    public void setChoices(List<McqChoice> choices) {
        this.choices = choices;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }
    
    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public List<SubjectTag> getSubjectTags() {
        return subjectTags;
    }

    public void setSubjectTags(List<SubjectTag> subjectTags) {
        this.subjectTags = subjectTags;
    }

    public List<QuestionPart> getParts() {
        return parts;
    }

    public void setParts(List<QuestionPart> parts) {
        this.parts = parts;
    }

    public List<ExamSection> getSections() {
        return sections;
    }

    public void setSections(List<ExamSection> sections) {
        this.sections = sections;
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
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sg.edu.nus.iss.ems.entity.Question[ id=" + id + " ]";
    }
    
}
