package cupid.main.business.impl;

import cupid.main.business.repository.SwipeRepository;
import cupid.main.business.repository.UserRepository;
import cupid.main.business.service.SwipeService;
import cupid.main.controller.domain.Handler.CallResponse;
import cupid.main.controller.domain.Swipe.CreateSwipeRequest;
import cupid.main.controller.domain.Swipe.CreateSwipeResponse;
import cupid.main.controller.domain.Swipe.GetSwipesByUserIdResponse;
import cupid.main.controller.domain.Swipe.Swipe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SwipeServiceImpl implements SwipeService {
    SwipeRepository swipeRepository;
    UserRepository userRepository;
    @Override
    public CallResponse<CreateSwipeResponse> createCreateSwipe(CreateSwipeRequest request) {
        if(!userRepository.getUserById(request.getOrigin_userId()).hasValue()) {
            return new CallResponse<>(404, "Origin user not found");
        }
        if (!userRepository.getUserById(request.getTarget_userId()).hasValue()) {
            return new CallResponse<>(404, "Target user not found");
        }
        if(request.getOrigin_userId() == request.getTarget_userId()) {
            return new CallResponse<>(400, "User cannot swipe own account");
        }

        return swipeRepository.createSwipe(request);

    }

    @Override
    public CallResponse<GetSwipesByUserIdResponse> getSwipesByUserId(Integer id) {
        if (!userRepository.getUserById(id).hasValue()) {
            return new CallResponse<>(400, "User does not exist");
        }

        return swipeRepository.getSwipesByUserId(id);

    }
}
