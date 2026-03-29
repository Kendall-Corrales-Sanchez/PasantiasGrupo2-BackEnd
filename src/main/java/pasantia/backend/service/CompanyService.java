package pasantia.backend.service;

import pasantia.backend.entity.Companies;

import java.util.List;

public interface CompanyService {
    Companies save(Companies company);
    List<Companies> findAll();
    Companies findById(Integer id);
    void deleteById(Integer id);
    Companies update(Companies company);
    
}
