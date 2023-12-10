package cupid.main.business.impl;

import cupid.main.business.service.ForYouService;
import cupid.main.domain.Dto.User.GetUserResponse;
import cupid.main.domain.Entity.Preference;
import cupid.main.domain.Entity.Swipe;
import cupid.main.domain.Entity.User;
import cupid.main.domain.adapter.PreferenceAdapter;
import cupid.main.domain.adapter.SwipeAdapter;
import cupid.main.domain.adapter.UserAdapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ForYouServiceImpl implements ForYouService {
    UserAdapter userRepository;
    PreferenceAdapter preferenceRepository;
    SwipeAdapter swipeRepository;

    @Override
    public List<GetUserResponse> GenerateForYouList(Integer userId) {
        User currentUser = userRepository.getUserById(userId);
        Preference preferences = preferenceRepository.getPreferenceById(userId);
        List<Swipe> userSwipes = swipeRepository.getSwipesByUserId(userId);

        List<Integer> swipedUsers = userSwipes.stream()
                .map(Swipe::getTarget_userId)
                .toList();

        List<User> userSource = new ArrayList<>();
        List<Integer> potentialMatches = new ArrayList<>();


        return null;
    }
}
