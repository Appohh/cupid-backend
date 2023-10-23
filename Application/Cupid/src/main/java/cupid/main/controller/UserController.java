package cupid.main.controller;

import cupid.main.business.impl.UserServiceImpl;
import cupid.main.controller.dto.Preference.GetPreferenceResponse;
import cupid.main.controller.dto.Preference.UpdatePreferenceRequest;
import cupid.main.controller.dto.User.*;
import cupid.main.domain.Dto.Preference.UpdatePreference;
import cupid.main.domain.Dto.User.CreateUser;
import cupid.main.domain.Dto.User.CreateUserResponse;
import cupid.main.domain.Dto.User.GetUserResponse;
import cupid.main.domain.Dto.User.UserLogin;
import cupid.main.domain.Entity.Preference;
import cupid.main.domain.Entity.User;
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

    @PostMapping("create")
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

    @PostMapping("/authenticate")
    public ResponseEntity<GetUserResponse> authenticateUser(@RequestBody @Valid UserLogin request) {
        User authorizedUser = userService.authenticateUser(request);

        GetUserResponse userResponse = GetUserResponse.fromUser(authorizedUser);

        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping("validatePreference")
    public ResponseEntity<GetPreferenceResponse> userPreferenceFilled(@RequestBody @Valid int userId) {
        User user = userService.getUserById(userId);

        if (userService.userFilledPreference(user)){
            return ResponseEntity.ok().body(GetPreferenceResponse.fromPreference(userService.getUserPreference(user)));
        }

        return null;
    }

    @PostMapping("validatePreference")
    public ResponseEntity<GetPreferenceResponse> updateUserPreference(@RequestBody @Valid UpdatePreferenceRequest request) {
        Preference preference = userService.updateUserPreference(userService.getUserById(request.getUserId()), UpdatePreference.fromRequest(request));

        return ResponseEntity.ok().body(GetPreferenceResponse.fromPreference(preference));
    }

}
