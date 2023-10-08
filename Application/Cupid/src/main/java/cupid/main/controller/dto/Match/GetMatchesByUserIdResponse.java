package cupid.main.controller.dto.Match;

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
