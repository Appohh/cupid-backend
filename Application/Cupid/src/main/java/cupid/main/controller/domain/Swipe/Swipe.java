package cupid.main.controller.domain.Swipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Swipe {
    Integer id;
    Integer origin_userId;
    Integer target_userId;
    Integer direction;
    String timestamp;
}
