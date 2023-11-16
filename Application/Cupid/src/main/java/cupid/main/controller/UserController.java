package cupid.main.controller;

import com.sun.mail.iap.ConnectionException;
import cupid.main.business.impl.UserServiceImpl;
import cupid.main.business.impl.VerifyServiceImpl;
import cupid.main.controller.dto.Handler.custom_exceptions.InvalidException;
import cupid.main.controller.dto.Preference.GetPreferenceResponse;
import cupid.main.controller.dto.Preference.UpdatePreferenceRequest;
import cupid.main.controller.dto.User.*;
import cupid.main.domain.Dto.Preference.UpdatePreference;
import cupid.main.domain.Dto.User.*;
import cupid.main.domain.Entity.Preference;
import cupid.main.domain.Entity.User;
import cupid.main.domain.Entity.VerifyToken;
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
    private final VerifyServiceImpl verifyService;

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
    public ResponseEntity<Boolean> userPreferenceFilled(@RequestBody @Valid int UserId) {
        if(userService.userFilledPreference(userService.getUserById(UserId))) {
            return ResponseEntity.ok(true);
        }
        return null;
    }

    @PostMapping("updatePreference")
    public ResponseEntity<GetPreferenceResponse> updateUserPreference(@RequestBody @Valid UpdatePreferenceRequest request) {
        Preference preference = userService.updateUserPreference(userService.getUserById(request.getUserId()), UpdatePreference.fromRequest(request));

        return ResponseEntity.ok().body(GetPreferenceResponse.fromPreference(preference));
    }

    @PostMapping("createToken")
    public ResponseEntity<CreateVerifyResponse> createVerifyToken(@RequestBody @Valid CreateVerifyEmailRequest request){
        if(request.getEmail().isEmpty()) {
            throw new IllegalArgumentException("No email address received");
        }

        VerifyToken createdToken = verifyService.CreateToken();
        String verificationLink = "http://localhost:5173/verify/" + createdToken.getToken();
        verifyService.MailToken(request.getEmail(), verificationLink, createdToken);

        CreateVerifyResponse response = CreateVerifyResponse.builder()
                .token(createdToken.getToken())
                .build();

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("validateToken")
    public ResponseEntity<Boolean> validateVerifyToken(@RequestBody @Valid ValidateVerifyEmailRequest request){
        if(request.getToken().isEmpty()) {
            throw new IllegalArgumentException("No token received");
        }

        if (!verifyService.TokenValid(request.getToken())){
            throw new InvalidException("Token is invalid");
        }

        Boolean result = verifyService.VerifyToken(request.getToken());
        //TODO error?
        return ResponseEntity.ok().body(result);
    }

    @GetMapping({"{token}", "/verificationStatus/{token}"})
    public ResponseEntity<Integer> checkVerificationStatus(@PathVariable(value = "token") String token) {
        Integer status = verifyService.checkVerificationStatus(token);
        return ResponseEntity.ok().body(status);
    }
}
