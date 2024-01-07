package cupid.main.controller.dto.Swipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSwipeRequest {
    private Integer origin_userId;
    private Integer target_userId;
    private Integer direction;
}
