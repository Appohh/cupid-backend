package cupid.main.controller.dto.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUser {
    private String fName;
    private String lName;
    private String birthday;
    private String email;
    private String phone;
    private Integer gender;
    private String password;
    private Integer preferenceId;
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
                .preferenceId(request.getPreferenceId())
                .locationId(request.getLocationId())
                .pImage(request.getPImage())
                .bio(request.getBio())
                .build();
    }

}
