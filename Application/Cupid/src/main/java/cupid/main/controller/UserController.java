package cupid.main.controller;

import cupid.main.business.impl.UserServiceImpl;
import cupid.main.controller.domain.Handler.CallResponse;
import cupid.main.controller.domain.User.CreateUserRequest;
import cupid.main.controller.domain.User.CreateUserResponse;
import cupid.main.controller.domain.User.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") int id) {
        Optional<User> optionalUser = userService.GetUserById(id);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(optionalUser.get());
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        CallResponse<CreateUserResponse> response = userService.CreateUser(request);
        if(response.Failed()) {
             switch (response.getErrorCode()) {
                case 400 -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, response.getErrorMessage());
                case 404 -> throw new ResponseStatusException(HttpStatus.NOT_FOUND, response.getErrorMessage());
                default -> throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, response.getErrorMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(response.getValue());
        }
    }



}
