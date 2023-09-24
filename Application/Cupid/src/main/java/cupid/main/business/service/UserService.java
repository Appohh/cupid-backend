package cupid.main.business.service;

import cupid.main.controller.domain.Handler.CallResponse;
import cupid.main.controller.domain.User.CreateUserResponse;
import cupid.main.controller.domain.User.User;
import cupid.main.controller.domain.User.CreateUserRequest;

public interface UserService {
    CallResponse<CreateUserResponse> createUser(CreateUserRequest userRequest);
    CallResponse<User> getUserById(Integer id);
}
