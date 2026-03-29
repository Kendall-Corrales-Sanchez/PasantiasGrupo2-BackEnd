package pasantia.backend.serviceImplement;

import org.springframework.stereotype.Service;
import pasantia.backend.entity.Companies;
import pasantia.backend.repository.CompanyRepository;
import pasantia.backend.service.CompanyService;

import java.util.List;

@Service
public class CompanyServiceImplement implements CompanyService {

    private CompanyRepository companyRepository;
    public CompanyServiceImplement(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Companies save(Companies company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Companies> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Companies findById(Integer id) {
        return companyRepository.findById(id).get();
    }

    @Override
    public void deleteById(Integer id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Companies update(Companies company) {
        return companyRepository.save(company);
    }
}
