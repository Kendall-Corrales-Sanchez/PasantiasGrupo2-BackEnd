package pasantia.backend.serviceImplement;

import org.springframework.stereotype.Service;
import pasantia.backend.entity.Students;
import pasantia.backend.repository.StudentRepository;
import pasantia.backend.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImplement implements StudentService {

    private StudentRepository studentRepository;
    public StudentServiceImplement(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Students save(Students student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Students> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Students findById(Integer id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Students update(Students student) {
        return studentRepository.save(student);
    }
}
