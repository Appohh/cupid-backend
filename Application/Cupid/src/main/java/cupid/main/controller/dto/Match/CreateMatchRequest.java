package cupid.main.controller.dto.Match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateMatchRequest {
    Integer userId1;
    Integer userId2;
}