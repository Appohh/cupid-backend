package cupid.main.business.impl;

import cupid.main.domain.Dto.Analytics.GetAnalyticsResponse;
import cupid.main.domain.adapter.MatchAdapter;
import cupid.main.domain.adapter.SwipeAdapter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AnalyticsServiceImplTest {
    @Test
    void testGetAnalytics() {
        // Mock dependencies
        SwipeAdapter swipeRepository = mock(SwipeAdapter.class);
        MatchAdapter matchRepository = mock(MatchAdapter.class);

        // Create an instance of the service to be tested
        AnalyticsServiceImpl analyticsService = new AnalyticsServiceImpl(swipeRepository, matchRepository);

        // Prepare test data
        int totalSwipesToday = 10;
        int totalSwipeLeftToday = 5;
        int totalMatchesToday = 3;

        // Mock the behavior of your dependencies
        when(swipeRepository.getTotalSwipesToday()).thenReturn(totalSwipesToday);
        when(swipeRepository.getTotalSwipeLeftToday()).thenReturn(totalSwipeLeftToday);
        when(matchRepository.getTotalMatchesToday()).thenReturn(totalMatchesToday);

        // Call the method to be tested
        GetAnalyticsResponse result = analyticsService.GetAnalytics();

        // Verify the interactions with the mocks
        // (You can also use Mockito.verify to check how many times these methods were called if needed)

        // Assert the expected result based on the test data
        assertEquals(totalSwipesToday, result.getSwipesToday());
        assertEquals(totalSwipeLeftToday, result.getSwipeLeftToday());
        assertEquals(totalMatchesToday, result.getMatchesToday());
    }

}