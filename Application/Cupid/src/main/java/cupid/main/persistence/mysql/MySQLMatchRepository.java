package cupid.main.persistence.mysql;

import cupid.main.domain.Entity.Match;
import cupid.main.domain.adapter.MatchAdapter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Profile("mysql")
public class MySQLMatchRepository implements MatchAdapter {
    @Override
    public Match createMatch(Match match) {
        return null;
    }

    @Override
    public Match getMatchByPair(Integer userId1, Integer userId2) {
        return null;
    }

    @Override
    public List<Match> getMatchesByUserId(Integer userId) {
        return null;
    }
}
