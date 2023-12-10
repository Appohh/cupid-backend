package cupid.main.persistence.iJpa;

import cupid.main.domain.Entity.Swipe;
import cupid.main.domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface iSwipeJpa extends JpaRepository<Swipe, Integer> {
    @Query("SELECT s FROM Swipe s WHERE s.origin_userId = :userId")
    List<Swipe> findSwipesByOriginUserId(Integer userId);}
