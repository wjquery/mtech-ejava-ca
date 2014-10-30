/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ems.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Wenyan
 */
@Entity
@Table(name = "examtemplate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Examtemplate.findAll", query = "SELECT e FROM Examtemplate e"),
    @NamedQuery(name = "Examtemplate.findById", query = "SELECT e FROM Examtemplate e WHERE e.id = :id"),
    @NamedQuery(name = "Examtemplate.findByCreatedOn", query = "SELECT e FROM Examtemplate e WHERE e.createdOn = :createdOn")})
public class ExamTemplate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Integer id;
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @OneToMany(mappedBy = "templateId")
    private List<ExamSession> examsessionList;
    @JoinColumn(name = "ModuleCode", referencedColumnName = "Code")
    @ManyToOne
    private Module moduleCode;

    public ExamTemplate() {
    }

    public ExamTemplate(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @XmlTransient
    public List<ExamSession> getExamsessionList() {
        return examsessionList;
    }

    public void setExamsessionList(List<ExamSession> examsessionList) {
        this.examsessionList = examsessionList;
    }

    public Module getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(Module moduleCode) {
        this.moduleCode = moduleCode;
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
        if (!(object instanceof ExamTemplate)) {
            return false;
        }
        ExamTemplate other = (ExamTemplate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sg.edu.nus.iss.ems.entity.Examtemplate[ id=" + id + " ]";
    }
    
}
