package cupid.main.persistence.impl;

import cupid.main.business.repository.MatchRepository;
import cupid.main.controller.domain.Handler.CallResponse;
import cupid.main.controller.domain.Handler.CustomExceptions.NotFoundException;
import cupid.main.controller.domain.Match.CreateMatchRequest;
import cupid.main.controller.domain.Match.CreateMatchResponse;
import cupid.main.controller.domain.Match.GetMatchesByUserIdResponse;
import cupid.main.controller.domain.Match.Match;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class MatchRepositoryImpl implements MatchRepository {
    private final List<Match> matches;
    private static int ID_DUMMY = 1;
    @Override
    public Match createMatch(Match match) {

        if (true){
            Match createdMatch = Match.builder()
                    .id(ID_DUMMY)
                    .userId1(match.getUserId1())
                    .userId2(match.getUserId2())
                    .timestamp("now").build();

            matches.add(createdMatch);
            ID_DUMMY++;

            return createdMatch;

        } else throw new IllegalArgumentException("Match could not be made");

    }

    @Override
    public Match getMatchByPair(Integer userId1, Integer userId2) {
        Optional<Match> foundMatch;

        foundMatch = matches.stream()
                .filter(match -> (match.getUserId1().equals(userId1) && match.getUserId2().equals(userId2))
                        || (match.getUserId1().equals(userId2) && match.getUserId2().equals(userId1)))
                .findFirst();

        if (foundMatch.isEmpty()) {
            throw new NotFoundException("Match not found");
        }

        return foundMatch.get();

    }

    @Override
    public List<Match> getMatchesByUserId(Integer userId) {
        return null;
    }
}
