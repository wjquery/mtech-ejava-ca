/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ems.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Wenyan
 */
@Entity
@Table(name = "examinstance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Examinstance.findAll", query = "SELECT e FROM Examinstance e"),
    @NamedQuery(name = "Examinstance.findById", query = "SELECT e FROM Examinstance e WHERE e.id = :id")})
public class ExamInstance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Integer id;
    @JoinColumn(name = "UserId", referencedColumnName = "Id")
    @ManyToOne
    private User userId;
    @JoinColumn(name = "ExamSessionId", referencedColumnName = "Id")
    @ManyToOne
    private ExamSession examSessionId;

    public ExamInstance() {
    }

    public ExamInstance(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public ExamSession getExamSessionId() {
        return examSessionId;
    }

    public void setExamSessionId(ExamSession examSessionId) {
        this.examSessionId = examSessionId;
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
        if (!(object instanceof ExamInstance)) {
            return false;
        }
        ExamInstance other = (ExamInstance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sg.edu.nus.iss.ems.entity.Examinstance[ id=" + id + " ]";
    }
    
}
