package cupid.main.domain.adapter;

import cupid.main.domain.Entity.Swipe;

import java.util.List;

public interface SwipeAdapter {
    Swipe createSwipe(Swipe swipe);
    List<Swipe> getSwipesByUserId(Integer userId);
    Swipe getSwipeRightByPair(Integer origin, Integer target);
    void deleteSwipeById(Integer id);
    Integer getTotalSwipesToday();
    Integer getTotalSwipeLeftToday();

}
