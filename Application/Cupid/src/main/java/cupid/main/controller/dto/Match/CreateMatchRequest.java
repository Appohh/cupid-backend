package cupid.main.controller.dto.Match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMatchRequest {
    Integer userId1;
    Integer userId2;
}
