package cupid.main.controller;

import cupid.main.business.impl.ForYouServiceImpl;
import cupid.main.business.impl.UserServiceImpl;
import cupid.main.domain.Dto.User.GetUserResponse;
import cupid.main.domain.Entity.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foryou")
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
public class ForYouController {
    private final ForYouServiceImpl forYouService;

    @GetMapping("/generateForYou/{userId}")
    @PreAuthorize("hasRole(1) or hasRole(2)")
    public ResponseEntity<List<GetUserResponse>> generateForYou(@PathVariable("userId") @Valid int userId) {

        List<User> users = forYouService.GenerateForYouList(userId);

        List<GetUserResponse> forYouList = users.stream()
                .map(GetUserResponse::fromUser)
                .toList();

        return ResponseEntity.ok().body(forYouList);
    }
}
