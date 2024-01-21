package cupid.main.controller;

import cupid.main.business.service.SwipeService;
import cupid.main.controller.dto.Swipe.CreateSwipeRequest;
import cupid.main.domain.Dto.Swipe.CreateSwipeResponse;
import cupid.main.domain.Dto.Swipe.GetSwipesByUserIdResponse;
import cupid.main.domain.Entity.Swipe;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/swipe")
@AllArgsConstructor
public class SwipeController {
    private final SwipeService swipeService;

    @PostMapping("/create")
    @PreAuthorize("hasRole(1) or hasRole(2)")
    public ResponseEntity<CreateSwipeResponse> createSwipe(@RequestBody @Valid CreateSwipeRequest request) {
        Swipe swipeCreated = swipeService.createSwipe(
                Swipe.builder()
                        .origin_userId(request.getOrigin_userId())
                        .target_userId(request.getTarget_userId())
                        .direction(request.getDirection())
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

    @PostMapping("/checkMatch")
    @PreAuthorize("hasRole(1) or hasRole(2)")
    public ResponseEntity<Boolean> checkSwipeRightMatch(@RequestBody @Valid CreateSwipeRequest request) {
        boolean result = swipeService.checkMatch(request.getOrigin_userId(), request.getTarget_userId());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteSwipeById(@PathVariable(value = "id") int id) {

        swipeService.deleteSwipeById(id);

        return ResponseEntity.ok().body(true);
    }
}
