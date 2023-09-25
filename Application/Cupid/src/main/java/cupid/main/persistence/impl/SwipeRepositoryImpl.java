package cupid.main.persistence.impl;

import cupid.main.business.repository.SwipeRepository;
import cupid.main.controller.domain.Handler.CallResponse;
import cupid.main.controller.domain.Handler.CustomExceptions.NotFoundException;
import cupid.main.controller.domain.Swipe.CreateSwipeRequest;
import cupid.main.controller.domain.Swipe.CreateSwipeResponse;
import cupid.main.controller.domain.Swipe.GetSwipesByUserIdResponse;
import cupid.main.controller.domain.Swipe.Swipe;
import cupid.main.controller.domain.User.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SwipeRepositoryImpl implements SwipeRepository {
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
