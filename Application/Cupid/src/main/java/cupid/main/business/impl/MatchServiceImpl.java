package cupid.main.business.impl;

import cupid.main.business.repository.MatchRepository;
import cupid.main.business.repository.UserRepository;
import cupid.main.business.service.MatchService;
import cupid.main.controller.dto.Handler.CustomExceptions.AlreadyExistException;
import cupid.main.controller.dto.Handler.CustomExceptions.NotFoundException;
import cupid.main.controller.dto.Match.Match;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class MatchServiceImpl implements MatchService {
    MatchRepository matchRepository;
    UserRepository userRepository;
    @Override
    public Match createMatch(Match match) {
        if(userRepository.getUserById(match.getUserId1()) == null) {
            throw new NotFoundException("User 1 not found");
        }
        if (userRepository.getUserById(match.getUserId2()) == null) {
            throw new NotFoundException("User 1 not found");
        }
        if(match.getUserId1() == match.getUserId2()) {
            throw new IllegalArgumentException("Cannot match same user");
        }
        if(getMatchByPair(match.getUserId1(), match.getUserId2()) != null) {
            throw new AlreadyExistException("Match already exists");
        }

       return matchRepository.createMatch(match);
    }

    @Override
    public Match getMatchByPair(Integer userId1, Integer userId2) {
        return matchRepository.getMatchByPair(userId1, userId2);
    }

    @Override
    public List<Match> getMatchesByUserId(Integer userId) {
        return matchRepository.getMatchesByUserId(userId);
    }
}
