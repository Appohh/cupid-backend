package cupid.main.persistence.mysql;

import cupid.main.domain.Entity.Preference;
import cupid.main.domain.adapter.PreferenceAdapter;
import cupid.main.persistence.iJpa.iPreferenceJpa;
import cupid.main.persistence.iJpa.iUserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

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
        return null;
    }

    @Override
    public Boolean PreferenceFilled(Preference preference) {
        return null;
    }

    @Override
    public void UpdatePreference(Preference preference) {

    }

    @Override
    public Preference setPreference(Integer userId, Integer preferenceId) {
        return null;
    }
}
