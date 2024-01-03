package cupid.main.business.impl;

import cupid.main.business.service.ForYouService;
import cupid.main.domain.Entity.Preference;
import cupid.main.domain.Entity.Swipe;
import cupid.main.domain.Entity.User;
import cupid.main.domain.adapter.PreferenceAdapter;
import cupid.main.domain.adapter.SwipeAdapter;
import cupid.main.domain.adapter.UserAdapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ForYouServiceImpl implements ForYouService {
    UserAdapter userRepository;
    PreferenceAdapter preferenceRepository;
    SwipeAdapter swipeRepository;

    @Override
    public List<User> GenerateForYouList(Integer userId) {
        //get user
        User currentUser = userRepository.getUserById(userId);
        //get preferences of user
        Preference preferences = preferenceRepository.getPreferenceById(currentUser.getPreferenceId());
        //get swipes of user
        List<Swipe> userSwipes = swipeRepository.getSwipesByUserId(currentUser.getId());

        //map target swipe userId's to list
        List<Integer> swipedUsers = userSwipes.stream()
                .map(Swipe::getTarget_userId)
                .toList();

        //get users by bodyType, ethnicity, gender
        List<User> userSource = userRepository.getUsersByPref(preferences);

        //filter already swiped users out and set in list
        List<User> notSwipedUsers = userSource.stream()
                .filter(user -> !swipedUsers.contains(user.getId()))
                .toList();






        return notSwipedUsers;
    }
}
