package cupid.main.domain.Dto.Match;

import cupid.main.domain.Entity.Match;
import cupid.main.domain.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GetMatchesByUserIdResponse {
    private List<User> Matches;
}
