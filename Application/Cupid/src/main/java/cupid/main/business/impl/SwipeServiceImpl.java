package cupid.main.business.impl;

import cupid.main.business.adapter.SwipeAdapter;
import cupid.main.business.adapter.UserAdapter;
import cupid.main.business.service.SwipeService;
import cupid.main.controller.dto.Handler.CustomExceptions.NotFoundException;
import cupid.main.domain.Entity.Swipe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
