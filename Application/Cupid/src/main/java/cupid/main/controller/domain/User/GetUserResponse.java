package cupid.main.controller.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponse {
    private Integer id;
    private String fName;
    private String lName;
    private String birthday;
    private String email;
    private String phone;
    private int gender;
    private String pImage;
    private String bio;

    public static GetUserResponse fromUser(User user) {
        return GetUserResponse.builder()
                .id(user.getId())
                .fName(user.getFName())
                .lName(user.getLName())
                .birthday(user.getBirthday())
                .email(user.getEmail())
                .phone(user.getPhone())
                .gender(user.getGender())
                .pImage(user.getPImage())
                .bio(user.getBio())
                .build();
    }
}


