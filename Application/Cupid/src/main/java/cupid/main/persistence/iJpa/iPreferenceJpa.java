package cupid.main.persistence.iJpa;

import cupid.main.domain.Entity.Preference;
import cupid.main.domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iPreferenceJpa extends JpaRepository<Preference, Integer> {
}
