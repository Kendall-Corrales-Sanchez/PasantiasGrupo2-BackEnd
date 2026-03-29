package pasantia.backend.entity;

import jakarta.persistence.*;
import pasantia.backend.enumes.DayWeek;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Internships {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nameInternship, description, funcionality, remuneration, requirement;
    private Date expirationDate;


    @Enumerated(EnumType.STRING)
    private DayWeek day;

    private LocalTime starTime;
    private LocalTime finishTime;

    //Relacions

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Provinces province;

    @ManyToOne
    @JoinColumn(name = "mode_id")
    private Modes mode;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Companies company;

    @ManyToMany
    @JoinTable(
            name = "internship_student",
            joinColumns = @JoinColumn(name = "internship_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Students> students;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameInternship() {
        return nameInternship;
    }

    public void setNameInternship(String nameInternship) {
        this.nameInternship = nameInternship;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFuncionality() {
        return funcionality;
    }

    public void setFuncionality(String funcionality) {
        this.funcionality = funcionality;
    }

    public String getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(String remuneration) {
        this.remuneration = remuneration;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public DayWeek getDay() {
        return day;
    }

    public void setDay(DayWeek day) {
        this.day = day;
    }

    public LocalTime getStarTime() {
        return starTime;
    }

    public void setStarTime(LocalTime starTime) {
        this.starTime = starTime;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalTime finishTime) {
        this.finishTime = finishTime;
    }

    public Provinces getProvince() {
        return province;
    }

    public void setProvince(Provinces province) {
        this.province = province;
    }

    public Modes getMode() {
        return mode;
    }

    public void setMode(Modes mode) {
        this.mode = mode;
    }

    public Companies getCompany() {
        return company;
    }

    public void setCompany(Companies company) {
        this.company = company;
    }
}
