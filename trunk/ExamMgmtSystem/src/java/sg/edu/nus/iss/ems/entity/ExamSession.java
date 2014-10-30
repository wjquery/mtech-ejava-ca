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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Wenyan
 */
@Entity
@Table(name = "examsession")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Examsession.findAll", query = "SELECT e FROM Examsession e"),
    @NamedQuery(name = "Examsession.findById", query = "SELECT e FROM Examsession e WHERE e.id = :id"),
    @NamedQuery(name = "Examsession.findByDate", query = "SELECT e FROM Examsession e WHERE e.date = :date"),
    @NamedQuery(name = "Examsession.findByStartTime", query = "SELECT e FROM Examsession e WHERE e.startTime = :startTime"),
    @NamedQuery(name = "Examsession.findByDuration", query = "SELECT e FROM Examsession e WHERE e.duration = :duration"),
    @NamedQuery(name = "Examsession.findByLocation", query = "SELECT e FROM Examsession e WHERE e.location = :location")})
public class ExamSession implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "StartTime")
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Column(name = "Duration")
    private Integer duration;
    @Size(max = 255)
    @Column(name = "Location")
    private String location;
    @JoinColumn(name = "TemplateId", referencedColumnName = "Id")
    @ManyToOne
    private ExamTemplate templateId;
    @OneToMany(mappedBy = "examSessionId")
    private List<ExamInstance> examinstanceList;

    public ExamSession() {
    }

    public ExamSession(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ExamTemplate getTemplateId() {
        return templateId;
    }

    public void setTemplateId(ExamTemplate templateId) {
        this.templateId = templateId;
    }

    @XmlTransient
    public List<ExamInstance> getExaminstanceList() {
        return examinstanceList;
    }

    public void setExaminstanceList(List<ExamInstance> examinstanceList) {
        this.examinstanceList = examinstanceList;
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
        if (!(object instanceof ExamSession)) {
            return false;
        }
        ExamSession other = (ExamSession) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sg.edu.nus.iss.ems.entity.Examsession[ id=" + id + " ]";
    }
    
}
