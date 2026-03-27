package pasantia.backend.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Modes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String mode;

    //Relations
    @OneToMany(mappedBy = "mode")
    private List<Internships> internships;


    public Modes() {
    }

    public Modes(Integer id, String mode) {
        this.id = id;
        this.mode = mode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
