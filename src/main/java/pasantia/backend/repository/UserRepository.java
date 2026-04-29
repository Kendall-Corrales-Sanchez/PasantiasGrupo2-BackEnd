package pasantia.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pasantia.backend.entity.Companies;
import pasantia.backend.entity.Users;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query("SELECT u FROM users u WHERE u.mail = :mail")
    Optional<Companies> findByMail(@Param("mail") String mail);
}
