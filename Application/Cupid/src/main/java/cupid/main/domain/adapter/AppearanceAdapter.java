package cupid.main.domain.adapter;

import cupid.main.domain.Entity.Appearance;
import cupid.main.domain.Entity.Preference;
import cupid.main.domain.Entity.User;

public interface AppearanceAdapter {
    Appearance createAppearance(User user);

    Appearance getAppearanceByUserId(Integer userId);

    Boolean appearanceFilled(Appearance appearance);
    Appearance updateAppearance(Appearance appearance);
}
