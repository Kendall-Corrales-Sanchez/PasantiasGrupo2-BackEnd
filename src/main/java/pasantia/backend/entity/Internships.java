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




}
