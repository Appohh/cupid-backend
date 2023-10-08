package cupid.main.controller.dto.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserLogin {
    private String Email;
    private String Password;
}
