package cupid.main.persistence.mysql;

import cupid.main.domain.Dto.User.CreateUser;
import cupid.main.domain.Entity.User;
import cupid.main.domain.adapter.UserAdapter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("mysql")
public class MySQLUserRepository implements UserAdapter  {


    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User createUser(CreateUser user) {
        return null;
    }

    @Override
    public boolean userExist(String email, String phone) {
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public String getUserHashAndSalt(String email) {
        return null;
    }
}