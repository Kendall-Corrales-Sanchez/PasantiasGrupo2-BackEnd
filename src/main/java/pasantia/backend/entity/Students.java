package pasantia.backend.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
public class Students extends Users{

    private String cv, interestCarrer;
    private Date birthday;

    //Relations
    @ManyToMany(mappedBy = "students")
    private List<Internships> internships;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Provinces province;

    public Students() {
        super();
    }

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
