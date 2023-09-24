package cupid.main.controller;

import cupid.main.business.impl.SwipeServiceImpl;
import cupid.main.controller.domain.Handler.CallResponse;
import cupid.main.controller.domain.Handler.ExceptionHandler;
import cupid.main.controller.domain.Swipe.CreateSwipeRequest;
import cupid.main.controller.domain.Swipe.CreateSwipeResponse;
import cupid.main.controller.domain.Swipe.GetSwipesByUserIdResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/swipe")
@AllArgsConstructor
public class SwipeController {
    private final SwipeServiceImpl swipeService;

    @PostMapping()
    public ResponseEntity<CreateSwipeResponse> createSwipe(@RequestBody @Valid CreateSwipeRequest request) {
        CallResponse<CreateSwipeResponse> response = swipeService.createCreateSwipe(request);
        if(response.Failed()) {
            //throw http status exception
            ExceptionHandler.handleResponseStatus(response.getErrorCode(), response.getErrorMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response.getValue());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetSwipesByUserIdResponse> getSwipesByUserId(@PathVariable(value = "id") int id) {
        CallResponse<GetSwipesByUserIdResponse> response = swipeService.getSwipesByUserId(id);

        if (response.Failed()) {
            //throw http status exception
            ExceptionHandler.handleResponseStatus(response.getErrorCode(), response.getErrorMessage());
        }
        return ResponseEntity.ok().body(response.getValue());
    }
}
