package cupid.main.persistence.impl;

import cupid.main.business.adapter.SwipeAdapter;
import cupid.main.controller.dto.Handler.CustomExceptions.NotFoundException;
import cupid.main.domain.Dto.Swipe.GetSwipesByUserIdResponse;
import cupid.main.domain.Entity.Swipe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class SwipeFakeRepository implements SwipeAdapter {
    private final List<Swipe> swipes;
    private static int ID_DUMMY = 1;
    @Override
    public Swipe createSwipe(Swipe swipe) {
        Swipe createdSwipe = Swipe.builder()
                .id(ID_DUMMY)
                .origin_userId(swipe.getOrigin_userId())
                .target_userId(swipe.getTarget_userId())
                .direction(swipe.getDirection())
                .timestamp("Now").build();

        if (true){
            ID_DUMMY++;
            swipes.add(createdSwipe);
            return createdSwipe;
        } else throw new IllegalArgumentException("Incorrect resource provided");


    }

    @Override
    public List<Swipe> getSwipesByUserId(Integer userId) {
        GetSwipesByUserIdResponse swipeResponse = new GetSwipesByUserIdResponse(new ArrayList<>());

        List<Swipe> foundSwipes = swipes.stream()
                .filter(swipe -> swipe.getOrigin_userId().intValue() == userId).toList();


        if (foundSwipes.isEmpty()) {
            throw new NotFoundException("No swipes found");
        }

        return foundSwipes;
    }
}
