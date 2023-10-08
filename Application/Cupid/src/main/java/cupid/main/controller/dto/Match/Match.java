package cupid.main.controller.dto.Match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    Integer id;
    Integer userId1;
    Integer userId2;
    String timestamp;
}
