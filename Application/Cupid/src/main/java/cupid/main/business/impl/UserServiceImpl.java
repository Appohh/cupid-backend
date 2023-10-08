package cupid.main.business.impl;

import cupid.main.business.Security;
import cupid.main.business.service.UserService;
import cupid.main.controller.dto.Handler.CustomExceptions.AlreadyExistException;
import cupid.main.controller.dto.User.CreateUser;
import cupid.main.controller.dto.User.User;
import cupid.main.business.repository.UserRepository;
import cupid.main.controller.dto.User.UserLogin;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    @Override
    public User createUser(CreateUser user) {
        if (userRepository.userExist(user.getEmail(), user.getPhone())) {
            throw new AlreadyExistException("User already exists");
        }
        return userRepository.createUser(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.getUserById(id);
    }

    @Override
    public Boolean userCredentialsValid(UserLogin attempt) {
        String storedHashAndSalt = userRepository.getUserHashAndSalt(attempt.getEmail());
        return Security.verifyPassword(attempt.getPassword(), storedHashAndSalt);
        //TODO: implement validation service
    }
}
