package cupid.main.persistence.iJpa;

import cupid.main.domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface iUserJpa extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    User findUserByEmail(String email);
    @Query("SELECT u.password FROM User u WHERE u.email = :email")
    Optional<String> findPasswordByEmail(@Param("email") String email);

}
