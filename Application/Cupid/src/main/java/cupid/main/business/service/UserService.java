package cupid.main.business.service;

import cupid.main.domain.Dto.Appearance.UpdateAppearance;
import cupid.main.domain.Dto.Preference.UpdatePreference;
import cupid.main.domain.Dto.User.CreateUser;
import cupid.main.domain.Entity.Appearance;
import cupid.main.domain.Entity.Preference;
import cupid.main.domain.Entity.User;
import cupid.main.domain.Dto.User.UserLogin;

public interface UserService {
    User createUser(CreateUser user);
    User getUserById(Integer id);
    String authenticateUser(UserLogin attempt);
    Preference updateUserPreference(User user, UpdatePreference preference);

    boolean userFilledPreference(User user);

    Preference getUserPreference(User user);

    Appearance createAppearance(User user);

    boolean userFilledAppearance(User user);

    Appearance updateAppearance(UpdateAppearance updateAppearance);


    Integer validateReferralCode(String code);
}
