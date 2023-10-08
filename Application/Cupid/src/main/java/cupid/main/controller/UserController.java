package cupid.main.controller;

import cupid.main.business.impl.UserServiceImpl;
import cupid.main.controller.dto.User.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:5173")
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        User createdUser = userService.createUser(CreateUser.fromRequest(request));
        CreateUserResponse response = CreateUserResponse.builder().id(createdUser.getId()).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable(value = "id") int id) {
        User foundUser = userService.getUserById(id);
        GetUserResponse response = GetUserResponse.fromUser(foundUser);

        return ResponseEntity.ok().body(response);
    }
}
