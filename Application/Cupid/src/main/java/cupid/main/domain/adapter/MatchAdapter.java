package cupid.main.domain.adapter;

import cupid.main.domain.Entity.Match;

import java.util.List;

public interface MatchAdapter {
    Match createMatch(Match match);
    Match getMatchByPair(Integer userId1, Integer userId2);
    List<Match> getMatchesByUserId(Integer userId);
}
