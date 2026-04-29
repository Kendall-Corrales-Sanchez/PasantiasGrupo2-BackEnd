package pasantia.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pasantia.backend.DTOs.LoginAnswerDTO;
import pasantia.backend.DTOs.LoginDTO;
import pasantia.backend.entity.Companies;
import pasantia.backend.repository.CompanyRepository;
import pasantia.backend.security.JwtService;
import pasantia.backend.interfaces.CompanyService;

import java.util.List;

@Service
public class CompanyServiceImplement implements CompanyService {

    private CompanyRepository companyRepository;
    private JwtService jwtService;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public CompanyServiceImplement(CompanyRepository companyRepository, JwtService jwtService, BCryptPasswordEncoder encoder) {
        this.companyRepository = companyRepository;
        this.jwtService = jwtService;
        this.encoder = encoder;
    }

    // Para el register
    @Override
    public Companies save(Companies company) {
        // se crea una clave encriptada
        String encodedPassword = encoder.encode(company.getPassword());
        // Ahora le asignamos su contraseña encriptada random más su contraña y si alguien
        // pone la misma contraseña será distinta
        company.setPassword(encodedPassword);
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
    public LoginAnswerDTO login(LoginDTO request) {
        Companies company = companyRepository.findByMail(request.getMail())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));


        boolean passwordValid = encoder.matches(request.getPassword(), company.getPassword());

        if (!passwordValid) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // Genera token
        String token = jwtService.generateToken(company);
        return new LoginAnswerDTO(company, token);
    }
}
