package cupid.main.persistence.mysql;

import cupid.main.domain.Entity.Preference;
import cupid.main.domain.adapter.PreferenceAdapter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("mysql")
public class MySQLPreferenceRepository implements PreferenceAdapter {
    @Override
    public Preference createPreference() {
        return null;
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
}
