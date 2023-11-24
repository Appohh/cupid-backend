package cupid.main.domain.Dto.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateRole {
    private Integer userId;
    private Integer role;
}
