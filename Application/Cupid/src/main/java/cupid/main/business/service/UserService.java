package cupid.main.business.service;

import cupid.main.controller.dto.User.CreateUser;
import cupid.main.controller.dto.User.User;
import cupid.main.controller.dto.User.UserLogin;

public interface UserService {
    User createUser(CreateUser user);
    User getUserById(Integer id);
    User authenticateUser(UserLogin attempt);
}
