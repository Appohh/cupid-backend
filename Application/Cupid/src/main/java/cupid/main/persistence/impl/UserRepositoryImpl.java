package cupid.main.persistence.impl;

import cupid.main.controller.domain.Handler.CallResponse;
import cupid.main.controller.domain.User.User;
import cupid.main.controller.domain.User.CreateUserRequest;
import cupid.main.controller.domain.User.CreateUserResponse;
import cupid.main.business.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final List<User> users;

    @Override
    public Optional<User> getUserById(int id) {
        return users.stream()
            .filter(user -> user.getId() == id)
            .findFirst();
    }

    //TODO debug issue where user can still be made with missing fields
    @Override
    public CallResponse<CreateUserResponse> createUser(CreateUserRequest request) {
        User createdUser = User.builder()
        .id(999)
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

        return new CallResponse<>(CreateUserResponse.builder().id(createdUser.getId()).build());
    }

    @Override
    public boolean userExist(String email, String phone) {
        return false;
    }
}
