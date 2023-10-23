package cupid.main.persistence.impl;

import cupid.main.business.Security;
import cupid.main.controller.dto.Handler.CustomExceptions.NotFoundException;
import cupid.main.domain.Dto.User.CreateUser;
import cupid.main.domain.Entity.User;
import cupid.main.business.adapter.UserAdapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserFakeRepository implements UserAdapter {

    private final List<User> users;
    private static int ID_DUMMY = 2;


    @Override
    public User getUserById(int id) {
        Optional<User> foundUser = users.stream()
            .filter(user -> user.getId() == id)
            .findFirst();

        if (foundUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }

//        User firstUser = User.builder().id(1).fName("hello").lName("hi").gender(1).authId(1).bio("mybio").birthday("02-05-2001").locationId(1).email("emiail@ame.nl").phone("543524432").pImage("hi.png").preferenceId(1).build();
//
//        return firstUser;
        return foundUser.get();
    }

    //TODO debug user can still be made with missing fields

    @Override
    public User createUser(CreateUser user) {

            //user create, get id

            //hash
            String password = Security.hashPassword(user.getPassword());

            //preference create

            User createdUser = User.builder()
                    .id(ID_DUMMY)
                    .fName(user.getFName())
                    .lName(user.getLName())
                    .birthday(user.getBirthday())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .gender(user.getGender())
                    .password(password)
                    .locationId(user.getLocationId())
                    .pImage(user.getPImage())
                    .bio(user.getBio())
                    .build();

            users.add(createdUser);

            ID_DUMMY++;

            return createdUser;
    }

    @Override
    public boolean userExist(String email, String phone) {
        return users.stream()
                .anyMatch(user -> user.getEmail().equals(email) || user.getPhone().equals(phone));
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> user = users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();

        return user.orElseThrow(() ->
                new NotFoundException("User not found for email: " + email));
    }


    @Override
    public String getUserHashAndSalt(String email) {
        User user = getUserByEmail(email);

        return user.getPassword();
    }
}
