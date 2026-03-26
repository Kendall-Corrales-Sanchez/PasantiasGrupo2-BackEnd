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
}
