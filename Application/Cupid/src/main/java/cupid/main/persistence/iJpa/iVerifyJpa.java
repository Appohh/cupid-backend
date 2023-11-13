package cupid.main.persistence.iJpa;

import cupid.main.domain.Entity.VerifyToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface iVerifyJpa extends JpaRepository<VerifyToken, Integer> {
    VerifyToken findVerifyTokenByToken(String token);
    @Modifying
    @Query("UPDATE VerifyToken e SET e.verified = 1 WHERE e.token = :token")
    int validateToken(@Param("token") String token);
}
