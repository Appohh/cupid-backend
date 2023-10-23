package cupid.main.business.service;

import cupid.main.domain.Entity.Match;

import java.util.List;

public interface MatchService {
    Match createMatch(Match match);
    Match getMatchByPair(Integer userId1, Integer userId2);
    List<Match> getMatchesByUserId(Integer userId);
}
