package pasantia.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pasantia.backend.entity.Companies;

@Repository
public interface CompanyRepository extends JpaRepository<Companies, Integer> {
}
