package cupid.main.business.impl;

import cupid.main.business.service.ForYouService;
import cupid.main.domain.Entity.Preference;
import cupid.main.domain.Entity.Swipe;
import cupid.main.domain.Entity.User;
import cupid.main.domain.adapter.PreferenceAdapter;
import cupid.main.domain.adapter.SwipeAdapter;
import cupid.main.domain.adapter.UserAdapter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.ExpectedCount.times;

class ForYouServiceImplTest {

    @Test
    void testGenerateForYouList() {
        // Mock dependencies
        UserAdapter userRepository = mock(UserAdapter.class);
        PreferenceAdapter preferenceRepository = mock(PreferenceAdapter.class);
        SwipeAdapter swipeRepository = mock(SwipeAdapter.class);

        // Create an instance of the service to be tested
        ForYouService forYouService = new ForYouServiceImpl(userRepository, preferenceRepository, swipeRepository);

        // Prepare test data
        int userId = 1;
        User currentUser = new User(userId, "John", "Doe", "1990-01-01", "john.doe@example.com", "1234567890", 1, "test123", 1, 3, "test3", "test3");
        Preference preferences = new Preference(1, 1, 18, 5, 1, 4);
        List<Swipe> userSwipes = new ArrayList<>(List.of(new Swipe(null, 2, 1, 1, null)));
        userSwipes.add((new Swipe(null, 1, 2, 1, null)));

        // Mock the behavior of your dependencies
        when(userRepository.getUserById(userId)).thenReturn(currentUser);
        when(preferenceRepository.getPreferenceById(currentUser.getPreferenceId())).thenReturn(preferences);
        when(swipeRepository.getSwipesByUserId(userId)).thenReturn(userSwipes);
        when(userRepository.getUsersByPref(preferences)).thenReturn(List.of(
                new User(2, "Jane", "Doe", "1992-02-02", "jane.doe@example.com", "9876543210", 2, "test456", 1, 3, "test3", "test3"),
                new User(3, "Bob", "Smith", "1988-03-03", "bob.smith@example.com", "5556667777", 1, "test789", 1, 2, "test2", "test2"),
                new User(4, "Alice", "Johnson", "1995-04-04", "alice.johnson@example.com", "1112223333", 2, "test101", 1, 1, "test1", "test1")
        ));

        // Call the method to be tested
        List<User> result = forYouService.GenerateForYouList(userId);

        // Verify the interactions with the mocks
        // (You can also use Mockito.verify to check how many times these methods were called if needed)

        // Assert the expected result based on the test data
        assertEquals(2, result.size()); // Assuming only one user is not swiped
        assertEquals(3, result.get(0).getId()); // Assuming the user with ID 4 is not swiped
    }
}