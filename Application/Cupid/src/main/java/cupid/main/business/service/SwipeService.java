package cupid.main.business.service;

import cupid.main.controller.domain.Handler.CallResponse;
import cupid.main.controller.domain.Swipe.CreateSwipeRequest;
import cupid.main.controller.domain.Swipe.CreateSwipeResponse;
import cupid.main.controller.domain.Swipe.GetSwipesByUserIdResponse;
import cupid.main.controller.domain.Swipe.Swipe;


public interface SwipeService {
    CallResponse<CreateSwipeResponse> createCreateSwipe(CreateSwipeRequest request);
    CallResponse<GetSwipesByUserIdResponse> getSwipesByUserId(Integer id);
}
