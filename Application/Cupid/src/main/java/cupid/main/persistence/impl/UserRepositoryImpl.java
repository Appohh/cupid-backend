package cupid.main.persistence.impl;

import cupid.main.controller.domain.Handler.CallResponse;
import cupid.main.controller.domain.Handler.CustomExceptions.NotFoundException;
import cupid.main.controller.domain.User.CreateUser;
import cupid.main.controller.domain.User.User;
import cupid.main.controller.domain.User.CreateUserRequest;
import cupid.main.controller.domain.User.CreateUserResponse;
import cupid.main.business.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final List<User> users;
    private static int ID_DUMMY = 1;

    @Override
    public User getUserById(int id) {
        Optional<User> foundUser = users.stream()
            .filter(user -> user.getId() == id)
            .findFirst();

        if (foundUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        return foundUser.get();
    }

    //TODO debug user can still be made with missing fields
    @Override
    public User createUser(CreateUser user) {

        if(true) {

            //user create, get id

            //authentication create

            //preference create

            User createdUser = User.builder()
                    .id(ID_DUMMY)
                    .fName(user.getFName())
                    .lName(user.getLName())
                    .birthday(user.getBirthday())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .gender(user.getGender())
                    .preferenceId(user.getPreferenceId())
                    .locationId(user.getLocationId())
                    .pImage(user.getPImage())
                    .bio(user.getBio())
                    .build();

            users.add(createdUser);

            ID_DUMMY++;

            return createdUser;
        }
        else throw new IllegalArgumentException("Incorrect resource provided");
    }

    @Override
    public boolean userExist(String email, String phone) {
        //TODO test
        return users.stream()
                .anyMatch(user -> user.getEmail().equals(email) || user.getPhone().equals(phone));
    }
}
