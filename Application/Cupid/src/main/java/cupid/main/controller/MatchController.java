package cupid.main.controller;

import cupid.main.business.service.MatchService;
import cupid.main.controller.dto.Match.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
@AllArgsConstructor
public class MatchController {
    private final MatchService matchService;

    @PostMapping()
    public ResponseEntity<CreateMatchResponse> createMatch(@RequestBody @Valid CreateMatchRequest request) {
        Match matchCreated = matchService.createMatch(
                Match.builder()
                        .id(null)
                        .userId1(request.getUserId1())
                        .userId2(request.getUserId2())
                        .timestamp(null)
                        .build()
        );

        CreateMatchResponse response = CreateMatchResponse.builder().id(matchCreated.getId()).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetMatchesByUserIdResponse> getMatchesByUserId(@PathVariable(value = "id") int id) {
        List<Match> matchesFound = matchService.getMatchesByUserId(id);

        GetMatchesByUserIdResponse response = GetMatchesByUserIdResponse.builder().Matches(matchesFound).build();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{userId1}/{userId2}")
    public ResponseEntity<GetMatchResponse> getMatchesByUserId(  @PathVariable("userId1") int userId1, @PathVariable("userId2") int userId2) {
        Match matchFound = matchService.getMatchByPair(userId1, userId2);

        GetMatchResponse response = GetMatchResponse.fromMatch(matchFound);

        return ResponseEntity.ok().body(response);
    }
}
