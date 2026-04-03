package pasantia.backend.service;

import pasantia.backend.entity.Companies;
import pasantia.backend.security.Login;

import java.util.List;

public interface CompanyService {
    Companies save(Companies company);
    List<Companies> findAll();
    Companies findById(Integer id);
    void deleteById(Integer id);
    Companies update(Companies company);
    Login login(String mail, String password);
}
