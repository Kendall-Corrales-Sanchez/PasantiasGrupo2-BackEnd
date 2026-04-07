package pasantia.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Companies extends People{

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
    public Companies(Integer id, String name, String mail, String password, String whoAreWe, Sectores sector, List<Internships> internships) {
        super(id, name, mail, password);
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
