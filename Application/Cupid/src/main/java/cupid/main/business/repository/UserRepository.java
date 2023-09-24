package cupid.main.business.repository;

import cupid.main.controller.domain.Handler.CallResponse;
import cupid.main.controller.domain.User.CreateUserResponse;
import cupid.main.controller.domain.User.User;
import cupid.main.controller.domain.User.CreateUserRequest;

import java.util.Optional;

public interface UserRepository {
    CallResponse<User> getUserById(int id);
    CallResponse<CreateUserResponse> createUser(CreateUserRequest request);
    boolean userExist(String email, String phone);

}
