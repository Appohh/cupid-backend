package cupid.main.business;

import cupid.main.controller.domain.Handler.CallResponse;
import cupid.main.controller.domain.User.CreateUserResponse;
import cupid.main.controller.domain.User.User;
import cupid.main.controller.domain.User.CreateUserRequest;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getUserById(int id);
    CallResponse<CreateUserResponse> createUser(CreateUserRequest request);
    boolean userExist(String email, String phone);

}
