package cupid.main.business.impl;

import cupid.main.config.Security;
import cupid.main.config.security.token.impl.AccessTokenEncoderDecoderImpl;
import cupid.main.config.security.token.impl.AccessTokenImpl;
import cupid.main.domain.Dto.Appearance.UpdateAppearance;
import cupid.main.domain.Dto.Role.CreateRole;
import cupid.main.domain.Entity.Appearance;
import cupid.main.domain.adapter.AppearanceAdapter;
import cupid.main.domain.adapter.PreferenceAdapter;
import cupid.main.business.service.UserService;
import cupid.main.config.custom_exceptions.AlreadyExistException;
import cupid.main.config.custom_exceptions.NotFoundException;
import cupid.main.config.custom_exceptions.UnAuthorizedException;
import cupid.main.domain.Dto.Preference.UpdatePreference;
import cupid.main.domain.Dto.User.CreateUser;
import cupid.main.domain.Entity.Preference;
import cupid.main.domain.Entity.User;
import cupid.main.domain.adapter.RoleAdapter;
import cupid.main.domain.adapter.UserAdapter;
import cupid.main.domain.Dto.User.UserLogin;
import cupid.main.domain.other.ImageService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    UserAdapter userRepository;
    PreferenceAdapter preferenceRepository;
    RoleAdapter roleAdapter;
    AppearanceAdapter appearanceRepository;
    AccessTokenEncoderDecoderImpl accessToken;
    ImageService imageService;


    @Override
    @Transactional
    public User createUser(CreateUser user) {
        if (userRepository.userExist(user.getEmail(), user.getPhone())) {
            throw new AlreadyExistException("User already exists");
        }

        //create preference
        Preference preference = preferenceRepository.createPreference();

        //create image and get path
        String createdImagePath = imageService.SaveImage(user.getPImage());

        //set image path in user object
        user.setPImage(createdImagePath);

        //create the user
        User createdUser = userRepository.createUser(user, preference.getId());

        //create appearance
        appearanceRepository.createAppearance(createdUser);

        //set role for the created user
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
//        roles.add(roleAdapter.getRole(loggedUser.getId()));
        roles.add(1);
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
            .ethnicity(preference.getEthnicity())
            .build();
        preferenceRepository.UpdatePreference(newPreference);
        return newPreference;
    }
    @Override
    public boolean userFilledPreference(User user){
        if(user.getPreferenceId() == null){
            throw new IllegalArgumentException("User has no preference id");
        }

        return preferenceRepository.PreferenceFilled(preferenceRepository.getPreferenceById(user.getPreferenceId()));
    }
    @Override
    public Preference getUserPreference(User user) {
        return preferenceRepository.getPreferenceById(user.getPreferenceId());
    }
    @Override
    public Appearance createAppearance(User user){
        return appearanceRepository.createAppearance(user);
    }
    @Override
    public boolean userFilledAppearance(User user){
        Appearance appearance = appearanceRepository.getAppearanceByUserId(user.getId());
        return appearanceRepository.appearanceFilled(appearance);
    }
    @Override
    public Appearance updateAppearance(UpdateAppearance updateAppearance){
        Appearance newAppearance = Appearance.builder()
                .userId(updateAppearance.getUserId())
                .gender(updateAppearance.getGender())
                .bodyType(updateAppearance.getBodyType())
                .location(updateAppearance.getLocation())
                .ethnicity(updateAppearance.getEthnicity())
                .build();
        return appearanceRepository.updateAppearance(newAppearance);
    }

}
