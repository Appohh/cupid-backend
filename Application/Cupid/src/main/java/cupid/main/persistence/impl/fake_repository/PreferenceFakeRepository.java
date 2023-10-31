package cupid.main.persistence.impl.fake_repository;

import cupid.main.domain.adapter.PreferenceAdapter;
import cupid.main.controller.dto.Handler.custom_exceptions.NotFoundException;
import cupid.main.domain.Entity.Preference;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("fake")
@AllArgsConstructor
public class PreferenceFakeRepository implements PreferenceAdapter {
    private final List<Preference> preferences;
    private static int ID_DUMMY = 1;
    @Override
    public Preference createPreference() {

        Preference createdPreference = Preference.builder().id(ID_DUMMY).build();

        preferences.add(createdPreference);

        ID_DUMMY++;

        return createdPreference;
    }

    @Override
    public Preference getPreferenceById(int id) {
        Optional<Preference> foundPreference = preferences.stream()
                .filter(preference -> preference.getId() == id)
                .findFirst();

        if (foundPreference.isEmpty()) {
            throw new NotFoundException("Preference not found");
        }

        return foundPreference.get();
    }

    @Override
    public Boolean PreferenceFilled(Preference preference) {
        return preference.getId() != null
                && preference.getGender() != null
                && preference.getAge() != null
                && preference.getDistance() != null
                && preference.getBodyType() != null
                && preference.getEthnicity() != null
                && preference.getHeight() != null
                && preference.getWeight() != null;
    }

    @Override
    public void UpdatePreference(Preference preference) {

        if (getPreferenceById(preference.getId()) == null) {
            throw new NotFoundException("Preference not found");
        }

        preferences.removeIf(pref -> pref.getId().equals(preference.getId()));
        preferences.add(preference);

    }
}
