package cupid.main.business.impl;

import cupid.main.business.Security;
import cupid.main.config.security.token.impl.AccessTokenEncoderDecoderImpl;
import cupid.main.config.security.token.impl.AccessTokenImpl;
import cupid.main.domain.Dto.Role.CreateRole;
import cupid.main.domain.adapter.PreferenceAdapter;
import cupid.main.business.service.UserService;
import cupid.main.controller.dto.Handler.custom_exceptions.AlreadyExistException;
import cupid.main.controller.dto.Handler.custom_exceptions.NotFoundException;
import cupid.main.controller.dto.Handler.custom_exceptions.UnAuthorizedException;
import cupid.main.domain.Dto.Preference.UpdatePreference;
import cupid.main.domain.Dto.User.CreateUser;
import cupid.main.domain.Entity.Preference;
import cupid.main.domain.Entity.User;
import cupid.main.domain.adapter.RoleAdapter;
import cupid.main.domain.adapter.UserAdapter;
import cupid.main.domain.Dto.User.UserLogin;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    UserAdapter userRepository;
    PreferenceAdapter preferenceRepository;
    RoleAdapter roleAdapter;
    AccessTokenEncoderDecoderImpl accessToken;


    @Override
    @Transactional
    public User createUser(CreateUser user) {
        if (userRepository.userExist(user.getEmail(), user.getPhone())) {
            throw new AlreadyExistException("User already exists");
        }

        Preference preference = preferenceRepository.createPreference();
        User createdUser = userRepository.createUser(user, preference.getId());
        CreateRole roleCreate = CreateRole.builder()
                .userId(createdUser.getId())
                .role(user.getRole())
                .build();
        roleAdapter.createRole(roleCreate);

        return createdUser;
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.getUserById(id);
    }

    @Override
    public String authenticateUser(UserLogin attempt) {
        if (!userRepository.userExist(attempt.getEmail(), "")) {
            throw new NotFoundException("User with email: " + attempt.getEmail() + " does not exist");
        }
        String storedHashAndSalt = userRepository.getUserHashAndSalt(attempt.getEmail());

        if(!Security.verifyPassword(attempt.getPassword(), storedHashAndSalt)) {
            throw new UnAuthorizedException("Provided credentials are invalid");
        }

        User loggedUser = userRepository.getUserByEmail(attempt.getEmail());

        List<Integer> roles = new ArrayList<>();
        roles.add(roleAdapter.getRole(loggedUser.getId()));
        AccessTokenImpl jwt = new AccessTokenImpl(loggedUser.getEmail(), loggedUser.getId(), roles);

        return accessToken.encode(jwt);
    }

    @Override
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
