/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ems.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wenyan
 */
@Entity
@Table(name = "subject_tag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubjectTag.findAll", query = "SELECT s FROM SubjectTag s WHERE s.status = 1"),
    @NamedQuery(name = "SubjectTag.findById", query = "SELECT s FROM SubjectTag s WHERE s.id = :id AND s.status = 1"),
    @NamedQuery(name = "SubjectTag.findByTag", query = "SELECT s FROM SubjectTag s WHERE s.tag = :tag AND s.status = 1"),
    @NamedQuery(name = "SubjectTag.findByStatus", query = "SELECT s FROM SubjectTag s WHERE s.status = :status")})
public class SubjectTag implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "Tag")
    private String tag;
    @Column(name = "Status")
    private Integer status;

    @ManyToMany(mappedBy = "subjectTags")
    @JsonBackReference
    private List<Question> questions;
    
    @ManyToMany(mappedBy = "tags")
    private List<ExamSection> sections;
    
    public SubjectTag() {
    }

    public SubjectTag(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
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
        if (!(object instanceof SubjectTag)) {
            return false;
        }
        SubjectTag other = (SubjectTag) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sg.edu.nus.iss.ems.entity.SubjectTag[ id=" + id + " ]";
    }
    
}
