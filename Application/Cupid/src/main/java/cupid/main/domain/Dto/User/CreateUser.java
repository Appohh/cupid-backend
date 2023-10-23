package cupid.main.domain.Dto.User;

import cupid.main.controller.dto.User.CreateUserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateUser {
    private String fName;
    private String lName;
    private String birthday;
    private String email;
    private String phone;
    private Integer gender;
    private String password;
    private Integer locationId;
    private String pImage;
    private String bio;

    public static CreateUser fromRequest(CreateUserRequest request) {
        return CreateUser.builder()
                .fName(request.getFName())
                .lName(request.getLName())
                .birthday(request.getBirthday())
                .email(request.getEmail())
                .phone(request.getPhone())
                .gender(request.getGender())
                .password(request.getPassword())
                .locationId(request.getLocationId())
                .pImage(request.getPImage())
                .bio(request.getBio())
                .build();
    }

}
