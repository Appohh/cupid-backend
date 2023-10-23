package cupid.main.domain.Entity;

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
    private Integer gender;
    private String password;
    private Integer preferenceId;
    private Integer locationId;
    private String pImage;
    private String bio;

}
