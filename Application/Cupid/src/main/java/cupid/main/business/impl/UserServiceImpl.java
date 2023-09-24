package cupid.main.business.impl;

import cupid.main.business.service.UserService;
import cupid.main.controller.domain.Handler.CallResponse;
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
    public CallResponse<CreateUserResponse> createUser(CreateUserRequest request) {
        if (userRepository.userExist(request.getEmail(), request.getPhone())) {
            return new CallResponse<>(409,"User already exists");
        }
        return userRepository.createUser(request);
    }

    @Override
    public CallResponse<User> getUserById(Integer id) {
        return userRepository.getUserById(id);
    }
}
