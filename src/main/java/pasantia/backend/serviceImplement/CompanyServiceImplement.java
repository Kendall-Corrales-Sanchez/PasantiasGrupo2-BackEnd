package pasantia.backend.serviceImplement;

import org.springframework.stereotype.Service;
import pasantia.backend.entity.Companies;
import pasantia.backend.repository.CompanyRepository;
import pasantia.backend.security.JwtService;
import pasantia.backend.security.Login;
import pasantia.backend.service.CompanyService;

import java.util.List;

@Service
public class CompanyServiceImplement implements CompanyService {

    private CompanyRepository companyRepository;
    private JwtService jwtService;
    public CompanyServiceImplement(CompanyRepository companyRepository, JwtService jwtService) {
        this.companyRepository = companyRepository;
        this.jwtService = jwtService;
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

    @Override
    public Login login(String mail, String password) {
        Companies company = companyRepository.login(mail, password)
        .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));
        String token = jwtService.generateToken(company);

        return new Login(
                company.getId(),
                company.getName(),
                company.getPassword(),
                company.getMail(),
                token
        );
    }
}
