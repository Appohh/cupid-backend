package cupid.main.business.repository;

import cupid.main.controller.domain.Handler.CallResponse;
import cupid.main.controller.domain.Match.CreateMatchRequest;
import cupid.main.controller.domain.Match.CreateMatchResponse;

import cupid.main.controller.domain.Match.GetMatchesByUserIdResponse;
import cupid.main.controller.domain.Match.Match;

import java.util.List;

public interface MatchRepository {
    Match createMatch(Match match);
    Match getMatchByPair(Integer userId1, Integer userId2);
   List<Match> getMatchesByUserId(Integer userId);
}
