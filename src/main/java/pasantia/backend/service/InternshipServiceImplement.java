package pasantia.backend.service;

import org.springframework.stereotype.Service;
import pasantia.backend.DTOs.InternshipDTO;
import pasantia.backend.entity.Internships;
import pasantia.backend.repository.InternshipsRepository;
import pasantia.backend.interfaces.InternshipService;

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
    public List<InternshipDTO> findAll() {

        List<Internships> internships = internshipsRepository.findAll();

        return internships.stream().map(i -> {
            InternshipDTO dto = new InternshipDTO();

            dto.setId(i.getId());
            dto.setNameInternship(i.getNameInternship());
            dto.setDescription(i.getDescription());
            dto.setFuncionality(i.getFuncionality());
            dto.setRemuneration(i.getRemuneration());
            dto.setRequirement(i.getRequirement());
            dto.setExpirationDate(i.getExpirationDate());
            dto.setDay(i.getDay());
            dto.setStarTime(i.getStarTime());
            dto.setFinishTime(i.getFinishTime());

            // Relaciones (evitando null pointer)
            dto.setProvinceName(
                    i.getProvince() != null ? i.getProvince().getProvinceName() : null
            );

            dto.setModeName(
                    i.getMode() != null ? i.getMode().getMode() : null
            );

            dto.setCompanyName(
                    i.getCompany() != null ? i.getCompany().getName() : null
            );

            return dto;

        }).toList();
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
