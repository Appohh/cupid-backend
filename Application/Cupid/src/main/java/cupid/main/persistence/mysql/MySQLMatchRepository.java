package cupid.main.persistence.mysql;

import cupid.main.domain.Entity.Match;
import cupid.main.domain.adapter.MatchAdapter;
import cupid.main.persistence.iJpa.iMatchJpa;
import cupid.main.persistence.iJpa.iPreferenceJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Profile("mysql")
public class MySQLMatchRepository implements MatchAdapter {
    private iMatchJpa jpa;

    @Autowired
    public MySQLMatchRepository(iMatchJpa iMatchJpa){this.jpa = iMatchJpa;}
    @Override
    public Match createMatch(Match match) {
        return jpa.save(match);
    }

    @Override
    public Match getMatchByPair(Integer userId1, Integer userId2) {
        return jpa.findMatchByUserIds(userId1, userId2);
    }

    @Override
    public List<Match> getMatchesByUserId(Integer userId) {
        return jpa.findAllMatchesByUserId(userId);
    }

    @Override
    public Integer getTotalMatchesToday() {
        return jpa.countAllByTimestamp();
    }
}
