package cupid.main.domain.Dto.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateVerifyResponse {
    private String token;
}
