package cupid.main.persistence.iJpa;

import cupid.main.domain.Entity.Appearance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iAppearanceJpa extends JpaRepository<Appearance, Integer> {
    Appearance findByUserId(Integer userId);
}
