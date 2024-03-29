/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ems.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "question_part")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuestionPart.findAll", query = "SELECT q FROM QuestionPart q"),
    @NamedQuery(name = "QuestionPart.findById", query = "SELECT q FROM QuestionPart q WHERE q.id = :id"),
    @NamedQuery(name = "QuestionPart.findByPartName", query = "SELECT q FROM QuestionPart q WHERE q.partName = :partName")})
public class QuestionPart implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 1)
    @Column(name = "PartName")
    private String partName;
    
    @JoinColumn(name = "ParentId", referencedColumnName = "Id")
    @ManyToOne
    @JsonBackReference
    private Question parent;
    
    @JoinColumn(name = "QID", referencedColumnName = "Id")
    @ManyToOne
    @JsonBackReference
    private Question question;

    public QuestionPart() {
    }

    public QuestionPart(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Question getParent() {
        return parent;
    }

    public void setParent(Question parent) {
        this.parent = parent;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
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
        if (!(object instanceof QuestionPart)) {
            return false;
        }
        QuestionPart other = (QuestionPart) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sg.edu.nus.iss.ems.entity.QuestionPart[ id=" + id + " ]";
    }
    
}
