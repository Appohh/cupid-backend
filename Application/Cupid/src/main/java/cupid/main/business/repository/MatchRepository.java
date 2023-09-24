package cupid.main.business.repository;

import cupid.main.controller.domain.Handler.CallResponse;
import cupid.main.controller.domain.Match.CreateMatchRequest;
import cupid.main.controller.domain.Match.CreateMatchResponse;

import cupid.main.controller.domain.Match.GetMatchesByUserIdResponse;
import cupid.main.controller.domain.Match.Match;

public interface MatchRepository {
    CallResponse<CreateMatchResponse> createMatch(CreateMatchRequest request);
    CallResponse<Match> getMatchByPair(Integer userId1, Integer userId2);
    CallResponse<GetMatchesByUserIdResponse>getMatchesByUserId(Integer userId);
}
