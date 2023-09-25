package cupid.main.business.service;

import cupid.main.controller.domain.Swipe.CreateSwipeRequest;
import cupid.main.controller.domain.Swipe.Swipe;

import java.util.List;


public interface SwipeService {
    Swipe createSwipe(Swipe swipe);
    List<Swipe> getSwipesByUserId(Integer id);
}
