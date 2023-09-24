package cupid.main.controller.domain.Swipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateSwipeRequest {
    Integer origin_userId;
    Integer target_userId;
    Integer direction;
}
