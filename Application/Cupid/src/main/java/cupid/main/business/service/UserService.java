package cupid.main.business.service;

import cupid.main.domain.Dto.Preference.UpdatePreference;
import cupid.main.domain.Dto.User.CreateUser;
import cupid.main.domain.Entity.User;
import cupid.main.domain.Dto.User.UserLogin;

public interface UserService {
    User createUser(CreateUser user);
    User getUserById(Integer id);
    User authenticateUser(UserLogin attempt);
    User updateUserPreference(UpdatePreference preference, Integer preferenceId);
}
