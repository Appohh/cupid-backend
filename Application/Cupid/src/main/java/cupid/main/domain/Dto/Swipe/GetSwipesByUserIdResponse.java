package cupid.main.domain.Dto.Swipe;

import cupid.main.domain.Entity.Swipe;
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
