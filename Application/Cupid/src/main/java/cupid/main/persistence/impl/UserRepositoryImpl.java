package cupid.main.persistence.impl;

import cupid.main.controller.domain.Handler.CallResponse;
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
    public CallResponse<User> getUserById(int id) {
        Optional<User> foundUser = users.stream()
            .filter(user -> user.getId() == id)
            .findFirst();

        if (foundUser.isEmpty()) {
            return new CallResponse<>(404, "User not found");
        }

        return new CallResponse<>(foundUser.get());
    }

    //TODO debug user can still be made with missing fields
    @Override
    public CallResponse<CreateUserResponse> createUser(CreateUserRequest request) {

        if(true) {

            User createdUser = User.builder()
                    .id(ID_DUMMY)
                    .fName(request.getFName())
                    .lName(request.getLName())
                    .birthday(request.getBirthday())
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .gender(request.getGender())
                    .preferenceId(request.getPreferenceId())
                    .locationId(request.getLocationId())
                    .pImage(request.getPImage())
                    .bio(request.getBio())
                    .build();

            users.add(createdUser);

            ID_DUMMY++;

            return new CallResponse<>(CreateUserResponse.builder().id(createdUser.getId()).build());
        }
        else return new CallResponse<>(409, "Incorrect resource");
    }

    //TODO implement userExist()
    @Override
    public boolean userExist(String email, String phone) {
        return false;
    }
}
