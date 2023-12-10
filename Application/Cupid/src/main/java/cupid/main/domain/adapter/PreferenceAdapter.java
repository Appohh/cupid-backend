package cupid.main.domain.adapter;

import cupid.main.domain.Entity.Preference;

public interface PreferenceAdapter {
    Preference createPreference();
    Preference getPreferenceById(int id);
    Boolean PreferenceFilled(Preference preference);
    Preference UpdatePreference(Preference preference);
}
