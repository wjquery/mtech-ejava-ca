package sg.edu.nus.iss.ems.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "exam_template")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamTemplate.findAll", query = "SELECT e FROM ExamTemplate e"),
    @NamedQuery(name = "ExamTemplate.findById", query = "SELECT e FROM ExamTemplate e WHERE e.id = :id"),
    @NamedQuery(name = "ExamTemplate.findByName", query = "SELECT e FROM ExamTemplate e WHERE e.name = :name"),
    @NamedQuery(name = "ExamTemplate.findByCreatedOn", query = "SELECT e FROM ExamTemplate e WHERE e.createdOn = :createdOn")})
public class ExamTemplate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    
    @Size(max = 255)
    @Column(name = "Name")
    private String name;
    
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    
    @JoinColumn(name = "ModuleCode", referencedColumnName = "Code")
    @ManyToOne
    private Module module;
    
    @OneToMany(mappedBy = "template")
    private List<ExamSection> sections;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Module getModuleCode() {
        return module;
    }

    public void setModuleCode(Module moduleCode) {
        this.module = moduleCode;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
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
        return "sg.edu.nus.iss.ems.entity.ExamTemplate[ id=" + id + " ]";
    }

}
