package cupid.main.business.impl;

import cupid.main.business.repository.SwipeRepository;
import cupid.main.business.repository.UserRepository;
import cupid.main.business.service.SwipeService;
import cupid.main.controller.domain.Handler.CallResponse;
import cupid.main.controller.domain.Handler.CustomExceptions.NotFoundException;
import cupid.main.controller.domain.Swipe.CreateSwipeRequest;
import cupid.main.controller.domain.Swipe.CreateSwipeResponse;
import cupid.main.controller.domain.Swipe.GetSwipesByUserIdResponse;
import cupid.main.controller.domain.Swipe.Swipe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SwipeServiceImpl implements SwipeService {
    SwipeRepository swipeRepository;
    UserRepository userRepository;
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
