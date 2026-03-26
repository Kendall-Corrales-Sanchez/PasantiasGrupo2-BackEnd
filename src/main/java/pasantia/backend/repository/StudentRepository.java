package pasantia.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pasantia.backend.entity.Students;

public interface StudentRepository extends JpaRepository<Students, Integer> {
}
