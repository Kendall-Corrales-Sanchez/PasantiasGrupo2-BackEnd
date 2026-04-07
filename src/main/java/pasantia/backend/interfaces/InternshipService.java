package pasantia.backend.interfaces;

import pasantia.backend.entity.Internships;

import java.util.List;

public interface InternshipService {
    Internships save(Internships internships);
    List<Internships> findAll();
    Internships findById(Integer id);
    void deleteById(Integer id);
    Internships update(Internships internships);

}
