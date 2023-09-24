package cupid.main.controller;

import cupid.main.business.impl.UserServiceImpl;
import cupid.main.controller.domain.Handler.CallResponse;
import cupid.main.controller.domain.Handler.ExceptionHandler;
import cupid.main.controller.domain.User.CreateUserRequest;
import cupid.main.controller.domain.User.CreateUserResponse;
import cupid.main.controller.domain.User.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        CallResponse<CreateUserResponse> response = userService.createUser(request);
        if(response.Failed()) {
            //throw http status exception
            ExceptionHandler.handleResponseStatus(response.getErrorCode(), response.getErrorMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response.getValue());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") int id) {
        CallResponse<User> response = userService.getUserById(id);

        if (response.Failed()) {
            //throw http status exception
            ExceptionHandler.handleResponseStatus(response.getErrorCode(), response.getErrorMessage());
        }
            return ResponseEntity.ok().body(response.getValue());
    }
}
