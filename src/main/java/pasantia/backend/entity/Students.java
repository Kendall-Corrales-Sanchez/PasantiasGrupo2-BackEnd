package pasantia.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.sql.Date;
import java.util.List;

@Entity
public class Students extends People{

    private String cv, interestCarrer;
    private Date birthday;

    //Relations
    @ManyToMany(mappedBy = "students")
    private List<Internships> internships;

    public Date getBirthday() { return birthday; }

    public void setBirthday(Date birthday) { this.birthday = birthday;}

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getInterestCarrer() {
        return interestCarrer;
    }

    public void setInterestCarrer(String interestCarrer) {
        this.interestCarrer = interestCarrer;
    }
}
