package cupid.main.business.adapter;

import cupid.main.domain.Entity.Preference;

public interface PreferenceAdapter {
    Preference createPreference();
    Preference getPreferenceById(int id);
    Boolean PreferenceFilled(Preference preference);
    void UpdatePreference(Preference preference);
}
