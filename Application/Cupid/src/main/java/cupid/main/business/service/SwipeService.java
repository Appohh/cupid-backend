package cupid.main.business.service;

import cupid.main.controller.dto.Swipe.Swipe;

import java.util.List;


public interface SwipeService {
    Swipe createSwipe(Swipe swipe);
    List<Swipe> getSwipesByUserId(Integer id);
}
