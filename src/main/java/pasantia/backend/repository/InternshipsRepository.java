package pasantia.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pasantia.backend.entity.Internships;

@Repository
public interface InternshipsRepository extends JpaRepository<Internships, Integer> {
}
