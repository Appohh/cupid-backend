package cupid.main.business.repository;

import cupid.main.controller.dto.Swipe.Swipe;

import java.util.List;

public interface SwipeRepository {
    Swipe createSwipe(Swipe swipe);
    List<Swipe> getSwipesByUserId(Integer userId);

}
