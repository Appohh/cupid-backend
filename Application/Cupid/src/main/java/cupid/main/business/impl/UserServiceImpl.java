package cupid.main.business.impl;

import cupid.main.business.service.UserService;
import cupid.main.controller.domain.Handler.CallResponse;
import cupid.main.controller.domain.Handler.CustomExceptions.AlreadyExistException;
import cupid.main.controller.domain.User.CreateUser;
import cupid.main.controller.domain.User.CreateUserRequest;
import cupid.main.controller.domain.User.CreateUserResponse;
import cupid.main.controller.domain.User.User;
import cupid.main.business.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
