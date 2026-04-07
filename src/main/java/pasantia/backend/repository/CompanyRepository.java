package pasantia.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pasantia.backend.entity.Companies;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Companies, Integer> {
    @Query(value = "SELECT * FROM companies WHERE mail = ?1 AND password = ?2", nativeQuery = true)
    Optional<Companies> login(String mail, String password);

    @Query(value = "SELECT * FROM companies WHERE mail = ?1", nativeQuery = true)
    Optional<Companies> findMail(String mail);
}
