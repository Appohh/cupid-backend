package cupid.main.persistence.iJpa;

import cupid.main.domain.Entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface iMatchJpa  extends JpaRepository<Match, Integer> {
    @Query("SELECT m FROM Match m WHERE (m.userId1 = :userId1 AND m.userId2 = :userId2) OR (m.userId1 = :userId2 AND m.userId2 = :userId1)")
    Match findMatchByUserIds(@Param("userId1") Integer userId1, @Param("userId2") Integer userId2);

    @Query("SELECT m FROM Match m WHERE m.userId1 = :userId OR m.userId2 = :userId")
    List<Match> findAllMatchesByUserId(@Param("userId") Integer userId);
}
