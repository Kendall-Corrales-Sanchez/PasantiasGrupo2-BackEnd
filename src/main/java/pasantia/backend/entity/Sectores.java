package pasantia.backend.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Sectores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nameSector;

    //Relations
    @OneToMany(mappedBy = "sector")
    private List<Companies> companies;


    public Sectores() {
    }

    public Sectores(Integer id, String nameSector) {
        this.id = id;
        this.nameSector = nameSector;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameSector() {
        return nameSector;
    }

    public void setNameSector(String nameSector) {
        this.nameSector = nameSector;
    }
}