package pasantia.backend.controller;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import pasantia.backend.entity.Internships;
import pasantia.backend.service.InternshipServiceImplement;

import java.util.List;

@Repository
@RequestMapping("/internship/probate")
public class InternshipController {

    private final InternshipServiceImplement internshipServiceImplement;
    public InternshipController(InternshipServiceImplement internshipServiceImplement) {
        this.internshipServiceImplement = internshipServiceImplement;
    }

    @PostMapping
    public Internships save(@RequestBody Internships internships){
        return internshipServiceImplement.save(internships);
    }

    @GetMapping
    public List<Internships> findAll(){
        return internshipServiceImplement.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        internshipServiceImplement.deleteById(id);
    }
    @PutMapping("/{id}")
    public Internships update(@RequestBody Internships internship, @PathVariable Integer id) {
        Internships update = internshipServiceImplement.findById(id);
        update.setNameInternship(internship.getNameInternship());
        update.setDescription(internship.getDescription());
        update.setFuncionality(internship.getFuncionality());
        update.setRemuneration(internship.getRemuneration());
        update.setRequirement(internship.getRequirement());
        update.setExpirationDate(internship.getExpirationDate());
        update.setDay(internship.getDay());
        update.setStarTime(internship.getStarTime());
        update.setFinishTime(internship.getFinishTime());
        return internshipServiceImplement.update(update);
    }
}
