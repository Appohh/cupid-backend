package cupid.main.business.repository;

import cupid.main.controller.dto.User.CreateUser;
import cupid.main.controller.dto.User.User;

public interface UserRepository {
    User getUserById(int id);
    User createUser(CreateUser user);
    boolean userExist(String email, String phone);
    String getUserHashAndSalt(String email);

}
