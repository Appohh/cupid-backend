package cupid.main.domain.Dto.Match;

import cupid.main.domain.Entity.Match;
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
