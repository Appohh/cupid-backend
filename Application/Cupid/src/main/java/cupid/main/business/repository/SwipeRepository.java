package cupid.main.business.repository;

import cupid.main.controller.domain.Handler.CallResponse;
import cupid.main.controller.domain.Swipe.CreateSwipeRequest;
import cupid.main.controller.domain.Swipe.CreateSwipeResponse;
import cupid.main.controller.domain.Swipe.GetSwipesByUserIdResponse;
import cupid.main.controller.domain.Swipe.Swipe;

import java.util.List;

public interface SwipeRepository {
    Swipe createSwipe(Swipe swipe);
    List<Swipe> getSwipesByUserId(Integer userId);

}
