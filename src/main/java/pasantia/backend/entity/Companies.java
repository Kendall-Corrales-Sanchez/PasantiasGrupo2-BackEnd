package pasantia.backend.entity;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Entity
public class Companies extends Users {

    private String whoAreWe;

    //relations
    @ManyToOne
    @JoinColumn(name = "sector_id")
    private Sectores sector;

    @OneToMany(mappedBy = "company")
    private List<Internships> internships;

    public Companies() {
        super();
    }
    public Companies(String whoAreWe, Sectores sector, List<Internships> internships) {
        this.whoAreWe = whoAreWe;
        this.sector = sector;
        this.internships = internships;
    }

    public String getWhoAreWe() {
        return whoAreWe;
    }

    public void setWhoAreWe(String whoAreWe) {
        this.whoAreWe = whoAreWe;
    }

    public Sectores getSector() {
        return sector;
    }

    public void setSector(Sectores sector) {
        this.sector = sector;
    }

    public List<Internships> getInternships() {
        return internships;
    }

    public void setInternships(List<Internships> internships) {
        this.internships = internships;
    }
}
