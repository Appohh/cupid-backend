package cupid.main.persistence.impl;

import cupid.main.business.repository.SwipeRepository;
import cupid.main.controller.domain.Handler.CallResponse;
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
    public CallResponse<CreateSwipeResponse> createSwipe(CreateSwipeRequest request) {
        Swipe createdSwipe = Swipe.builder()
                .id(ID_DUMMY)
                .origin_userId(request.getOrigin_userId())
                .target_userId(request.getTarget_userId())
                .direction(request.getDirection())
                .timestamp("Now").build();

        if (true){
            ID_DUMMY++;
            swipes.add(createdSwipe);
            return new CallResponse<>(CreateSwipeResponse.builder().id(createdSwipe.getId()).build());
        } else return new CallResponse<>(409, "Incorrect resource");


    }

    @Override
    public CallResponse<GetSwipesByUserIdResponse> getSwipesByUserId(Integer userId) {
        GetSwipesByUserIdResponse swipeResponse = new GetSwipesByUserIdResponse(new ArrayList<>());

        List<Swipe> foundSwipes = swipes.stream()
                .filter(swipe -> swipe.getOrigin_userId().intValue() == userId).toList();


        if (foundSwipes.isEmpty()) {
            return new CallResponse<>(404, "No swipes found");
        }

        swipeResponse.setSwipes(foundSwipes);
        return new CallResponse<>(swipeResponse);
    }
}
