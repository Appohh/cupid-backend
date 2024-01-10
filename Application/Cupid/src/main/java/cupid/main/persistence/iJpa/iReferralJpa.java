package cupid.main.persistence.iJpa;

import cupid.main.domain.Entity.Referral;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iReferralJpa extends JpaRepository<Referral, Integer> {
    Referral findByCode(String code);
}
