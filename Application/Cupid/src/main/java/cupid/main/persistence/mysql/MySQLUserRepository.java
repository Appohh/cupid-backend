package cupid.main.persistence.mysql;

import cupid.main.config.Security;
import cupid.main.domain.Dto.User.CreateUser;
import cupid.main.domain.Entity.Preference;
import cupid.main.domain.Entity.User;
import cupid.main.domain.adapter.UserAdapter;
import cupid.main.persistence.iJpa.iUserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        if (email.isEmpty() && phone.isEmpty()) {
            throw new IllegalArgumentException("No arguments given");
        }

        boolean emailExists = false;
        boolean phoneExists = false;

        if (!email.isEmpty()) {
            emailExists = jpa.existsByEmail(email);
        }

        if (!phone.isEmpty()) {
            phoneExists = jpa.existsByPhone(phone);
        }

        return (email.isEmpty() || emailExists) && (phone.isEmpty() || phoneExists);
    }

    @Override
    public User getUserByEmail(String email) {
        return jpa.findUserByEmail(email);
    }

    @Override
    public String getUserHashAndSalt(String email) {
        return jpa.findPasswordByEmail(email).get();
    }

    @Override
    public List<User> getUsersByPref(Preference preference) {
        return jpa.findAllByBodyEthnicityGender((preference));
    }

    @Override
    public List<User> getUsersById(List<Integer> userIds) {
        return jpa.findAllById(userIds);
    }

}