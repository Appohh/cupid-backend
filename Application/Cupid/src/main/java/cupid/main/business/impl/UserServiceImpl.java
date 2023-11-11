package cupid.main.business.impl;

import cupid.main.business.Security;
import cupid.main.domain.adapter.PreferenceAdapter;
import cupid.main.business.service.UserService;
import cupid.main.controller.dto.Handler.custom_exceptions.AlreadyExistException;
import cupid.main.controller.dto.Handler.custom_exceptions.NotFoundException;
import cupid.main.controller.dto.Handler.custom_exceptions.UnAuthorizedException;
import cupid.main.domain.Dto.Preference.UpdatePreference;
import cupid.main.domain.Dto.User.CreateUser;
import cupid.main.domain.Entity.Preference;
import cupid.main.domain.Entity.User;
import cupid.main.domain.adapter.UserAdapter;
import cupid.main.domain.Dto.User.UserLogin;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    UserAdapter userRepository;
    PreferenceAdapter preferenceRepository;


    @Override
    @Transactional
    public User createUser(CreateUser user) {
        if (userRepository.userExist(user.getEmail(), user.getPhone())) {
            throw new AlreadyExistException("User already exists");
        }

        Preference preference = preferenceRepository.createPreference();


        return userRepository.createUser(user, preference.getId());
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.getUserById(id);
    }

    @Override
    public User authenticateUser(UserLogin attempt) {
        if (!userRepository.userExist(attempt.getEmail(), "")) {
            throw new NotFoundException("User with email: " + attempt.getEmail() + " does not exist");
        }
        String storedHashAndSalt = userRepository.getUserHashAndSalt(attempt.getEmail());

        if(!Security.verifyPassword(attempt.getPassword(), storedHashAndSalt)) {
            throw new UnAuthorizedException("Provided credentials are invalid");
        }

        return userRepository.getUserByEmail(attempt.getEmail());
    }

    @Override
    public User updateUserPreference(UpdatePreference preference, Integer userId) {

    return null;
    }

    public Preference updateUserPreference(User user, UpdatePreference preference){
        Preference newPreference = Preference.builder()
            .id(user.getPreferenceId())
            .gender(preference.getGender())
            .bodyType(preference.getBodyType())
            .distance(preference.getDistance())
            .age(preference.getAge())
            .height(preference.getHeight())
            .build();
        preferenceRepository.UpdatePreference(newPreference);
        return newPreference;
    }

    public boolean userFilledPreference(User user){
        if(user.getPreferenceId() == null){
            throw new IllegalArgumentException("User has no preference id");
        }

        return preferenceRepository.PreferenceFilled(preferenceRepository.getPreferenceById(user.getPreferenceId()));
    }

    public Preference getUserPreference(User user) {
        return preferenceRepository.getPreferenceById(user.getPreferenceId());
    }

}
