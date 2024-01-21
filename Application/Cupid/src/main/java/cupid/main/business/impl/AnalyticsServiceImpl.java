package cupid.main.business.impl;

import cupid.main.business.service.AnalyticsService;
import cupid.main.domain.Dto.Analytics.GetAnalyticsResponse;
import cupid.main.domain.adapter.MatchAdapter;
import cupid.main.domain.adapter.SwipeAdapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {
    SwipeAdapter swipeRepository;
    MatchAdapter matchRepository;
    @Override
    public GetAnalyticsResponse GetAnalytics() {

        Integer totalSwipesToday = swipeRepository.getTotalSwipesToday();
        Integer totalSwipeLeftToday = swipeRepository.getTotalSwipeLeftToday();
        Integer totalMatchesToday = matchRepository.getTotalMatchesToday();

        return GetAnalyticsResponse.builder()
                .swipesToday(totalSwipesToday)
                .swipeLeftToday(totalSwipeLeftToday)
                .matchesToday(totalMatchesToday)
                .build();
    }
}
