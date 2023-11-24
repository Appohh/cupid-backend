package cupid.main.persistence.iJpa;

import cupid.main.domain.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleJpa extends JpaRepository<Role, Integer> {
   Optional<Role> findByUserId(int id);

}
