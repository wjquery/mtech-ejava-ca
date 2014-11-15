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

@Entity
@Table(name = "mcq_choice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "McqChoice.findAll", query = "SELECT m FROM McqChoice m"),
    @NamedQuery(name = "McqChoice.findById", query = "SELECT m FROM McqChoice m WHERE m.id = :id"),
    @NamedQuery(name = "McqChoice.findByChoice", query = "SELECT m FROM McqChoice m WHERE m.choice = :choice"),
    @NamedQuery(name = "McqChoice.findByChoiceText", query = "SELECT m FROM McqChoice m WHERE m.choiceText = :choiceText")})
public class McqChoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 1)
    @Column(name = "Choice")
    private String choice;
    @Size(max = 255)
    @Column(name = "Choice_Text")
    private String choiceText;
    
    @JoinColumn(name = "Question_Id", referencedColumnName = "Id")
    @ManyToOne
     @JsonBackReference
    private Question question;

    public McqChoice() {
    }

    public McqChoice(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getChoiceText() {
        return choiceText;
    }

    public void setChoiceText(String choiceText) {
        this.choiceText = choiceText;
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
        if (!(object instanceof McqChoice)) {
            return false;
        }
        McqChoice other = (McqChoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sg.edu.nus.iss.ems.entity.McqChoice[ id=" + id + " ]";
    }
    
}
