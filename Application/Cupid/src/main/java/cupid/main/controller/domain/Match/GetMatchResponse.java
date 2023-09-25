package cupid.main.controller.domain.Match;

import cupid.main.controller.domain.User.GetUserResponse;
import cupid.main.controller.domain.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMatchResponse {
    Integer id;
    Integer userId1;
    Integer userId2;
    String timestamp;

    public static GetMatchResponse fromMatch(Match match) {
        return GetMatchResponse.builder()
                .id(match.getId())
                .userId1(match.getUserId1())
                .userId2(match.getUserId2())
                .timestamp(match.getTimestamp())
                .build();
    }
}
