package cupid.main.controller.domain.Swipe;

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
