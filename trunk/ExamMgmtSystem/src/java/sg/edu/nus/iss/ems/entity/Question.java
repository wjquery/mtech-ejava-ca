/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ems.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q"),
    @NamedQuery(name = "Question.findById", query = "SELECT q FROM Question q WHERE q.id = :id"),
    @NamedQuery(name = "Question.findByQid", query = "SELECT q FROM Question q WHERE q.qid = :qid"),
    @NamedQuery(name = "Question.findByVersion", query = "SELECT q FROM Question q WHERE q.version = :version"),
    @NamedQuery(name = "Question.findByCreatedOn", query = "SELECT q FROM Question q WHERE q.createdOn = :createdOn"),
    @NamedQuery(name = "Question.findByMark", query = "SELECT q FROM Question q WHERE q.mark = :mark")})
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Integer id;
    @Column(name = "QID")
    private Integer qid;
    @Column(name = "Version")
    private Integer version;
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Mark")
    private Double mark;
    @Lob
    @Size(max = 65535)
    @Column(name = "QuestionText")
    private String questionText;
    @Lob
    @Column(name = "Status")
    private byte[] status;
    @JoinColumn(name = "ModuleCode", referencedColumnName = "Code")
    @ManyToOne
    private Module moduleCode;
    @JoinColumn(name = "CreatedBy", referencedColumnName = "Id")
    @ManyToOne
    private User createdBy;

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

    public byte[] getStatus() {
        return status;
    }

    public void setStatus(byte[] status) {
        this.status = status;
    }

    public Module getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(Module moduleCode) {
        this.moduleCode = moduleCode;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
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
