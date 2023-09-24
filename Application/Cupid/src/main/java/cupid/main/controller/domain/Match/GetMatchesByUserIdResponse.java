package cupid.main.controller.domain.Match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GetMatchesByUserIdResponse {
    private List<Match> Matches;
}
