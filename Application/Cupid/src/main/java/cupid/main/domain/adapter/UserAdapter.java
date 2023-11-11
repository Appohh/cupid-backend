package cupid.main.domain.adapter;

import cupid.main.domain.Dto.User.CreateUser;
import cupid.main.domain.Entity.User;

public interface UserAdapter {
    User getUserById(int id);
    User createUser(CreateUser user, Integer preferenceId);
    boolean userExist(String email, String phone);
    User getUserByEmail(String email);
    String getUserHashAndSalt(String email);

}
