package cupid.main.persistence.mysql;

import cupid.main.config.custom_exceptions.NotFoundException;
import cupid.main.domain.Entity.Appearance;
import cupid.main.domain.Entity.Preference;
import cupid.main.domain.Entity.User;
import cupid.main.domain.adapter.AppearanceAdapter;
import cupid.main.persistence.iJpa.iAppearanceJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("mysql")
public class MySQLAppearanceRepository implements AppearanceAdapter {
    private iAppearanceJpa jpa;

    @Autowired
    public MySQLAppearanceRepository(iAppearanceJpa iAppearanceJpa){this.jpa = iAppearanceJpa;}
    @Override
    public Appearance createAppearance(User user) {
        Appearance appearance = Appearance.builder().userId(user.getId()).build();
        return jpa.save(appearance);
    }

    @Override
    public Appearance getAppearanceByUserId(Integer userId){
        Optional<Appearance> foundAppearance = Optional.ofNullable(jpa.findByUserId(userId));
        if(foundAppearance.isEmpty()){
            throw new NotFoundException("Appearance was not found");
        }
        return foundAppearance.get();
    }

    @Override
    public Boolean appearanceFilled(Appearance appearance) {
        Optional<Appearance> foundAppearance = Optional.ofNullable(jpa.findByUserId(appearance.getUserId()));
        if (foundAppearance.isEmpty()){
            throw new NotFoundException("Appearance not found");
        }
        return foundAppearance.get().getBodyType() != null;
    }

    @Override
    public Appearance updateAppearance(Appearance appearance) {
        Appearance foundAppearance = getAppearanceByUserId(appearance.getUserId());
        appearance.setId(foundAppearance.getId());
        return jpa.save(appearance);
    }
}
