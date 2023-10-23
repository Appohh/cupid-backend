package cupid.main.business.service;

import cupid.main.domain.Entity.Swipe;

import java.util.List;


public interface SwipeService {
    Swipe createSwipe(Swipe swipe);
    List<Swipe> getSwipesByUserId(Integer id);
}
