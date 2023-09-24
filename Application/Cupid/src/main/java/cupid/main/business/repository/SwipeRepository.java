package cupid.main.business.repository;

import cupid.main.controller.domain.Handler.CallResponse;
import cupid.main.controller.domain.Swipe.CreateSwipeRequest;
import cupid.main.controller.domain.Swipe.CreateSwipeResponse;
import cupid.main.controller.domain.Swipe.GetSwipesByUserIdResponse;
import cupid.main.controller.domain.Swipe.Swipe;

public interface SwipeRepository {
    CallResponse<CreateSwipeResponse> createSwipe(CreateSwipeRequest request);
    CallResponse<GetSwipesByUserIdResponse>getSwipesByUserId(Integer userId);

}
