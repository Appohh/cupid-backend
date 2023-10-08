package cupid.main.business.repository;

import cupid.main.controller.dto.Match.Match;

import java.util.List;

public interface MatchRepository {
    Match createMatch(Match match);
    Match getMatchByPair(Integer userId1, Integer userId2);
   List<Match> getMatchesByUserId(Integer userId);
}
