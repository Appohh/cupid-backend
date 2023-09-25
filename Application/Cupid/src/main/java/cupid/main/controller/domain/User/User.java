package cupid.main.controller.domain.User;

import cupid.main.controller.domain.Match.GetMatchResponse;
import cupid.main.controller.domain.Match.Match;
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
    private int authId;
    private int preferenceId;
    private int locationId;
    private String pImage;
    private String bio;

}
