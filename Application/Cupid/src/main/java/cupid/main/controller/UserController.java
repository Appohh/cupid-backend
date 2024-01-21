package cupid.main.controller;

import cupid.main.business.impl.UserServiceImpl;
import cupid.main.business.impl.VerifyServiceImpl;
import cupid.main.config.custom_exceptions.InvalidException;
import cupid.main.controller.dto.Appearance.GetAppearanceResponse;
import cupid.main.controller.dto.Appearance.UpdateAppearanceRequest;
import cupid.main.controller.dto.Preference.GetPreferenceResponse;
import cupid.main.controller.dto.Preference.UpdatePreferenceRequest;
import cupid.main.controller.dto.User.*;
import cupid.main.domain.Dto.Appearance.UpdateAppearance;
import cupid.main.domain.Dto.Preference.UpdatePreference;
import cupid.main.domain.Dto.User.*;
import cupid.main.domain.Entity.Appearance;
import cupid.main.domain.Entity.Preference;
import cupid.main.domain.Entity.User;
import cupid.main.domain.Entity.VerifyToken;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final VerifyServiceImpl verifyService;

    @PostMapping("create")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        request.setLocationId(123);
        User createdUser = userService.createUser(CreateUser.fromRequest(request));
        CreateUserResponse response = CreateUserResponse.builder().id(createdUser.getId()).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("createAdmin")
    public ResponseEntity<CreateUserResponse> createAdmin(@RequestBody @Valid CreateUserRequest request) {
        request.setLocationId(123);
        User createdUser = userService.createUser(CreateUser.fromRequest(request));
        CreateUserResponse response = CreateUserResponse.builder().id(createdUser.getId()).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole(1) or hasRole(2)")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable(value = "id") int id) {
        User foundUser = userService.getUserById(id);
        GetUserResponse response = GetUserResponse.fromUser(foundUser);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody @Valid UserLogin request) {
        String jwt = userService.authenticateUser(request);
        return ResponseEntity.ok().body(jwt);
    }
    @GetMapping("/validatePreference/{userId}")
    public ResponseEntity<Boolean> validatePreference(@PathVariable("userId") @Valid int userId) {
        if (userService.userFilledPreference(userService.getUserById(userId))) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

    @PostMapping("/updatePreference")
    @PreAuthorize("hasRole(1) or hasRole(2)")
    public ResponseEntity<GetPreferenceResponse> updateUserPreference(@RequestBody @Valid UpdatePreferenceRequest request) {
        Preference preference = userService.updateUserPreference(userService.getUserById(request.getUserId()), UpdatePreference.fromRequest(request));

        return ResponseEntity.ok().body(GetPreferenceResponse.fromPreference(preference));
    }

    @GetMapping("/validateAppearance/{userId}")
    public ResponseEntity<Boolean> validateAppearance(@PathVariable("userId") @Valid int userId) {
        if (userService.userFilledAppearance(userService.getUserById(userId))) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

    @PostMapping("/updateAppearance")
    @PreAuthorize("hasRole(1) or hasRole(2)")
    public ResponseEntity<GetAppearanceResponse> updateUserAppearance(@RequestBody @Valid UpdateAppearanceRequest request) {
        Appearance appearance = userService.updateAppearance(UpdateAppearance.fromRequest(request));

        return ResponseEntity.ok().body(GetAppearanceResponse.fromAppearance(appearance));
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
    @GetMapping("/verificationStatus/{token}")
    public ResponseEntity<Integer> checkVerificationStatus(@PathVariable(value = "token") String token) {
        Integer status = verifyService.checkVerificationStatus(token);
        return ResponseEntity.ok().body(status);
    }

    @GetMapping("/referral/{code}")
    public ResponseEntity<Integer> checkReferral(@PathVariable(value = "code") String code) {
        Integer status = userService.validateReferralCode(code);
        return ResponseEntity.ok().body(status);
    }
}
