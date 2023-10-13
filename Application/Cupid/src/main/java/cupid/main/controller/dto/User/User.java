package cupid.main.controller.dto.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String fName;
    private String lName;
    private String birthday;
    private String email;
    private String phone;
    private int gender;
    private String password;
    private int preferenceId;
    private int locationId;
    private String pImage;
    private String bio;

}
