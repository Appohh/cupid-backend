package cupid.main.business.impl;

import cupid.main.business.Security;
import cupid.main.business.service.UserService;
import cupid.main.controller.dto.Handler.CustomExceptions.AlreadyExistException;
import cupid.main.controller.dto.Handler.CustomExceptions.NotFoundException;
import cupid.main.controller.dto.Handler.CustomExceptions.UnAuthorizedException;
import cupid.main.controller.dto.User.CreateUser;
import cupid.main.controller.dto.User.User;
import cupid.main.business.repository.UserRepository;
import cupid.main.controller.dto.User.UserLogin;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
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
}
