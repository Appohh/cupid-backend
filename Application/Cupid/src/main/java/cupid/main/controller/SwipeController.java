package cupid.main.controller;

import cupid.main.business.service.SwipeService;
import cupid.main.controller.domain.Swipe.CreateSwipeRequest;
import cupid.main.controller.domain.Swipe.CreateSwipeResponse;
import cupid.main.controller.domain.Swipe.GetSwipesByUserIdResponse;
import cupid.main.controller.domain.Swipe.Swipe;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/swipe")
@AllArgsConstructor
public class SwipeController {
    private final SwipeService swipeService;

    @PostMapping()
    public ResponseEntity<CreateSwipeResponse> createSwipe(@RequestBody @Valid CreateSwipeRequest request) {
        Swipe swipeCreated = swipeService.createSwipe(
                Swipe.builder()
                        .id(null)
                        .origin_userId(request.getOrigin_userId())
                        .target_userId(request.getTarget_userId())
                        .direction(request.getDirection())
                        .timestamp(null)
                        .build()
        );

        CreateSwipeResponse response = CreateSwipeResponse.builder().id(swipeCreated.getId()).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetSwipesByUserIdResponse> getSwipesByUserId(@PathVariable(value = "id") int id) {
       List<Swipe> swipesFound = swipeService.getSwipesByUserId(id);

       GetSwipesByUserIdResponse response = GetSwipesByUserIdResponse.builder().Swipes(swipesFound).build();

       return ResponseEntity.ok().body(response);
    }
}
