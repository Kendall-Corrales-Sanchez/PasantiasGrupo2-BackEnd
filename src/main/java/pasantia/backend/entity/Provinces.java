package pasantia.backend.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Provinces {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name province")
    private String provinceName;

    //Relations
    @OneToMany(mappedBy = "province")
    private List<Internships> internships;

    @OneToMany(mappedBy = "province")
    private List<Students> students;


    public Provinces() {
    }

    public Provinces(Integer id, String provinceName) {
        this.id = id;
        this.provinceName = provinceName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
