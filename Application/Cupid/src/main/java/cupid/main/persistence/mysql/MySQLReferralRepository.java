package cupid.main.persistence.mysql;

import cupid.main.config.custom_exceptions.NotFoundException;
import cupid.main.domain.Entity.Referral;
import cupid.main.domain.adapter.ReferralAdapter;
import cupid.main.persistence.iJpa.iPreferenceJpa;
import cupid.main.persistence.iJpa.iReferralJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("mysql")
public class MySQLReferralRepository implements ReferralAdapter {
    private iReferralJpa jpa;

    @Autowired
    public MySQLReferralRepository(iReferralJpa iReferralJpa){this.jpa = iReferralJpa;}

    @Override
    public Integer validateRefferalCode(String code) {
        Optional<Referral> foundReferral = Optional.ofNullable(jpa.findByCode(code));
        if(foundReferral.isEmpty()){
            throw new NotFoundException("Referral not found");
        }
        return 1;

    }
}
