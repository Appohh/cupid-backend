package cupid.main.controller;

import cupid.main.business.service.AnalyticsService;
import cupid.main.config.custom_exceptions.NotFoundException;
import cupid.main.domain.Dto.Analytics.GetAnalyticsResponse;
import cupid.main.domain.Dto.User.GetUserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analytics")
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor

public class AnalyticsController {
    private final AnalyticsService analyticsService;

    @GetMapping("/generateDashboard")
    @PreAuthorize("hasRole(2)")
    public ResponseEntity<GetAnalyticsResponse> generateDashboard() {
        GetAnalyticsResponse response;
        try {
            response = analyticsService.GetAnalytics();
        }
        catch (Exception ex) {
            throw new NotFoundException("Error trying to find analytics");
        }

        return ResponseEntity.ok(response);

    }
}
