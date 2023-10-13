package cupid.main.controller.dto.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
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
}
