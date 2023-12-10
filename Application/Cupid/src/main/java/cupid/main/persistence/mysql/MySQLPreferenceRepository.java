package cupid.main.persistence.mysql;

import cupid.main.config.custom_exceptions.NotFoundException;
import cupid.main.domain.Entity.Preference;
import cupid.main.domain.adapter.PreferenceAdapter;
import cupid.main.persistence.iJpa.iPreferenceJpa;
import cupid.main.persistence.iJpa.iUserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("mysql")
public class MySQLPreferenceRepository implements PreferenceAdapter {

    private iPreferenceJpa jpa;

    @Autowired
    public MySQLPreferenceRepository(iPreferenceJpa iPreferenceJpa){this.jpa = iPreferenceJpa;}
    @Override
    public Preference createPreference() {
        return jpa.save(new Preference());
    }

    @Override
    public Preference getPreferenceById(int id) {
        Optional<Preference> foundPreference = jpa.findById(id);
        if(foundPreference.isEmpty()){
            throw new NotFoundException("Preference was not found");
        }
        return foundPreference.get();
    }

    @Override
    public Boolean PreferenceFilled(Preference preference) {
        Optional<Preference> foundPreference = jpa.findById(preference.getId());
        if (foundPreference.isEmpty()){
            throw new NotFoundException("Preference not found");
        }
        if(foundPreference.get().getBodyType() == null){
            return false;
        }

        return true;
    }

    @Override
    public Preference UpdatePreference(Preference preference) {
        return jpa.save(preference);
    }

}
