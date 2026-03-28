package pasantia.backend.controller;

import org.springframework.web.bind.annotation.*;
import pasantia.backend.entity.Students;
import pasantia.backend.serviceImplement.StudentServiceImplement;

import java.util.List;

@RestController
@RequestMapping("/prueba")
public class StudentController {

    StudentServiceImplement studentServiceImplement;
    public StudentController(StudentServiceImplement studentServiceImplement) {
        this.studentServiceImplement = studentServiceImplement;
    }

    @GetMapping
    public List<Students> findAll(){
        return studentServiceImplement.findAll();
    }

    @PostMapping
    public Students save(@RequestBody Students student){
        return studentServiceImplement.save(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Integer id){
        studentServiceImplement.deleteById(id);
    }

    @PutMapping("/{id}")
    public Students updateStudent(@PathVariable Integer id, @RequestBody Students student) {

        Students update = studentServiceImplement.findById(id);

        update.setName(student.getName());
        update.setMail(student.getMail());
        update.setInterestCarrer(student.getInterestCarrer());
        update.setPassword(student.getPassword());
        update.setBirthday(student.getBirthday());

        return studentServiceImplement.update(update);
    }


}
