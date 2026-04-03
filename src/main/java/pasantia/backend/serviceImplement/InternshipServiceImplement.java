package pasantia.backend.serviceImplement;

import org.springframework.stereotype.Service;
import pasantia.backend.entity.Internships;
import pasantia.backend.repository.InternshipsRepository;
import pasantia.backend.service.InternshipService;

import java.util.List;

@Service
public class InternshipServiceImplement implements InternshipService {

    private InternshipsRepository internshipsRepository;
    public InternshipServiceImplement (InternshipsRepository internshipsRepository) {
        this.internshipsRepository = internshipsRepository;
    }

    @Override
    public Internships save(Internships internships) {
        return internshipsRepository.save(internships);
    }

    @Override
    public List<Internships> findAll() {
        return internshipsRepository.findAll();
    }

    @Override
    public Internships findById(Integer id) {
        return internshipsRepository.findById(id).get();
    }

    @Override
    public void deleteById(Integer id) {
        internshipsRepository.deleteById(id);
    }

    @Override
    public Internships update(Internships internships) {
        return internshipsRepository.save(internships);
    }
}
