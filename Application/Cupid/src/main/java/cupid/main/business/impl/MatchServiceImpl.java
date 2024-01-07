package cupid.main.business.impl;

import cupid.main.domain.Entity.User;
import cupid.main.domain.adapter.MatchAdapter;
import cupid.main.domain.adapter.UserAdapter;
import cupid.main.business.service.MatchService;
import cupid.main.config.custom_exceptions.AlreadyExistException;
import cupid.main.config.custom_exceptions.NotFoundException;
import cupid.main.domain.Entity.Match;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class MatchServiceImpl implements MatchService {
    MatchAdapter matchRepository;
    UserAdapter userRepository;
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
    public List<User> getMatchesByUserId(Integer userId) {
        List<Match> foundMatches = matchRepository.getMatchesByUserId(userId);
        List<Integer> targetUsers = foundMatches.stream()
                .flatMap(match -> Stream.of(match.getUserId1(), match.getUserId2()))
                .filter(id -> !id.equals(userId))
                .distinct()
                .collect(Collectors.toList());


        return userRepository.getUsersById(targetUsers);
    }
}
