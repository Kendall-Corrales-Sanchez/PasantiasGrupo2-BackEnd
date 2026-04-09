package pasantia.backend.DTOs;

import pasantia.backend.enumes.DayWeek;

import java.sql.Date;
import java.time.LocalTime;

public class InternshipDTO {
    private Integer id;
    private String nameInternship;
    private String description;
    private String funcionality;
    private String remuneration;
    private String requirement;
    private Date expirationDate;
    private DayWeek day;
    private LocalTime starTime;
    private LocalTime finishTime;

    // Datos simplificados de relaciones
    private String provinceName;
    private String modeName;
    private String companyName;

    // Constructor vacío
    public InternshipDTO() {}

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

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
