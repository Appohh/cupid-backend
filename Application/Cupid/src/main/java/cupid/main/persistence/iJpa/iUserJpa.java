package cupid.main.persistence.iJpa;

import cupid.main.domain.Dto.User.CreateUser;
import cupid.main.domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface iUserJpa extends JpaRepository<User, Integer> {

}
