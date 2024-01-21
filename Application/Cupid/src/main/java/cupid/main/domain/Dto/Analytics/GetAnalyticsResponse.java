package cupid.main.domain.Dto.Analytics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAnalyticsResponse {
    private Integer swipesToday;
    private Integer matchesToday;
    private Integer swipeLeftToday;
}
