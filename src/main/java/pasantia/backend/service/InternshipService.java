package pasantia.backend.service;

import pasantia.backend.entity.Internships;

import java.util.List;

public interface InternshipService {
    Internships save(Internships internships);
    List<Internships> findAll();
    Internships findById(Integer id);
    void deleteById(Integer id);
    Internships update(Internships internships);

}
