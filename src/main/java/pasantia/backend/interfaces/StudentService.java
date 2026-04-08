package pasantia.backend.interfaces;

import pasantia.backend.entity.Students;

import java.util.List;

public interface StudentService {
    Students save(Students student);
    List<Students> findAll();
    Students findById(Integer id);
    void deleteById(Integer id);
    Students update(Students student);
}
