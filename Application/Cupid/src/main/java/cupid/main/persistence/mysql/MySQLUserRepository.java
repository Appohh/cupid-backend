package cupid.main.persistence.mysql;

import cupid.main.business.Security;
import cupid.main.domain.Dto.User.CreateUser;
import cupid.main.domain.Entity.User;
import cupid.main.domain.adapter.UserAdapter;
import cupid.main.persistence.iJpa.iUserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("mysql")
public class MySQLUserRepository implements UserAdapter  {

    private iUserJpa jpa;

    @Autowired
    public MySQLUserRepository(iUserJpa iUserJpa){this.jpa = iUserJpa;}

    @Override
    public User getUserById(int id) {
       return jpa.findById(id).get();
    }

    @Override
    public User createUser(CreateUser user, Integer preferenceId) {

        String password = Security.hashPassword(user.getPassword());

        User createdUser = User.builder()
                .fName(user.getFName())
                .lName(user.getLName())
                .birthday(user.getBirthday())
                .email(user.getEmail())
                .phone(user.getPhone())
                .gender(user.getGender())
                .password(password)
                .preferenceId(preferenceId)
                .locationId(user.getLocationId())
                .pImage(user.getPImage())
                .bio(user.getBio())
                .build();

        return jpa.save(createdUser);
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