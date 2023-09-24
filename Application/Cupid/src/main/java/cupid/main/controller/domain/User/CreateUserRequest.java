package cupid.main.controller.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class CreateUserRequest {
    private String fName;
    private String lName;
    private String birthday;
    private String email;
    private String phone;
    private int gender;
    private int preferenceId;
    private int locationId;
    private String pImage;
    private String bio;
}
