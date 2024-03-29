package sg.edu.nus.iss.ems.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "module")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Module.findAll", query = "SELECT m FROM Module m WHERE m.status != 0"),
    @NamedQuery(name = "Module.findByCode", query = "SELECT m FROM Module m WHERE m.code = :code and m.status != 0"),
    @NamedQuery(name = "Module.findByName", query = "SELECT m FROM Module m WHERE m.name = :name and m.status != 0"),
    @NamedQuery(name = "Module.findByQuestionCount", query = "SELECT m FROM Module m WHERE m.questionCount = :questionCount and m.status != 0")})
public class Module implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Code")
    private String code;
    @Size(max = 255)
    @Column(name = "Name")
    private String name;
    @Column(name = "Status")
    private Integer status;
    
    @Column(name = "Question_Count")
    private Integer questionCount;
    
    @OneToMany(mappedBy = "module")
    private List<Question> questionList;
    
    @ManyToMany(mappedBy = "modules")
    private Set<User> users;

    @OneToMany(mappedBy = "module")
    private List<ExamTemplate> templates;
    
    public Module() {
    }

    public Module(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }

    @XmlTransient
    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    @XmlTransient
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Integer getStatus() {
        return status;
    }

    public List<ExamTemplate> getTemplates() {
        return templates;
    }

    public void setTemplates(List<ExamTemplate> templates) {
        this.templates = templates;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Module)) {
            return false;
        }
        Module other = (Module) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sg.edu.nus.iss.ems.entity.Module[ code=" + code + " ]";
    }
    
}
