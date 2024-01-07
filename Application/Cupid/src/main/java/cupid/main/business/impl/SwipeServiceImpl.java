package cupid.main.business.impl;

import cupid.main.domain.adapter.SwipeAdapter;
import cupid.main.domain.adapter.UserAdapter;
import cupid.main.business.service.SwipeService;
import cupid.main.config.custom_exceptions.NotFoundException;
import cupid.main.domain.Entity.Swipe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SwipeServiceImpl implements SwipeService {
    SwipeAdapter swipeRepository;
    UserAdapter userRepository;
    @Override
    public Swipe createSwipe(Swipe swipe) {
        if(userRepository.getUserById(swipe.getOrigin_userId()) == null) {
           throw new NotFoundException("Origin user not found");
        }
        if (userRepository.getUserById(swipe.getTarget_userId()) == null) {
            throw new NotFoundException("Target user not found");
        }
        if(swipe.getOrigin_userId() == swipe.getTarget_userId()) {
            throw new IllegalArgumentException("User cannot swipe own account");
        }

        return swipeRepository.createSwipe(swipe);

    }

    @Override
    public List<Swipe> getSwipesByUserId(Integer id) {
        if (userRepository.getUserById(id) == null) {
            throw new NotFoundException("User does not exist");
        }

        return swipeRepository.getSwipesByUserId(id);

    }

    @Override
    public Boolean checkMatch(Integer origin, Integer target) {
        Optional<Swipe> combo1 = Optional.ofNullable(swipeRepository.getSwipeRightByPair(origin, target));
        Optional<Swipe> combo2 = Optional.ofNullable(swipeRepository.getSwipeRightByPair(target, origin));

        if(combo1.isEmpty()) {
            return false;
        }

        return combo2.isPresent();

    }

    @Override
    public void deleteSwipeById(Integer id) {
        swipeRepository.deleteSwipeById(id);
    }
}
