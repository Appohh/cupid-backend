package cupid.main.controller.dto.Swipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GetSwipesByUserIdResponse {
    private List<Swipe> Swipes;
}
