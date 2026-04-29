package pasantia.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pasantia.backend.DTOs.LoginAnswerDTO;
import pasantia.backend.DTOs.LoginDTO;
import pasantia.backend.entity.Companies;
import pasantia.backend.entity.Users;
import pasantia.backend.repository.CompanyRepository;
import pasantia.backend.security.JwtService;
import pasantia.backend.interfaces.CompanyService;

import java.util.List;

@Service
public class CompanyServiceImplement implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImplement(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // Para el register
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
