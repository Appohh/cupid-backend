package cupid.main.business.impl;

import cupid.main.config.custom_exceptions.AlreadyExistException;
import cupid.main.config.custom_exceptions.NotFoundException;
import cupid.main.domain.Entity.Match;
import cupid.main.domain.Entity.User;
import cupid.main.domain.adapter.MatchAdapter;
import cupid.main.domain.adapter.UserAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MatchServiceImplTest {
    @Mock
    private MatchAdapter matchRepository;

    @Mock
    private UserAdapter userRepository;

    @InjectMocks
    private MatchServiceImpl matchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateMatch() {
        // Prepare test data
        Match match = Match.builder().userId1(1).userId2(2).build();

        // Mock the behavior of your dependencies
        when(userRepository.getUserById(1)).thenReturn(new User(1, "Jane", "Doe", "1992-02-02", "jane.doe@example.com", "9876543210", 2, "test456", 1, 3, "test3", "test3"));
        when(userRepository.getUserById(2)).thenReturn(new User(2, "Jane", "Doe", "1992-02-02", "jane.doe@example.com", "9876543210", 2, "test456", 1, 3, "test3", "test3"));
        when(matchRepository.getMatchByPair(1, 2)).thenReturn(null);
        when(matchRepository.createMatch(match)).thenReturn(match);

        // Call the method to be tested
        Match result = matchService.createMatch(match);

        // Verify the interactions with the mocks
        verify(userRepository, times(2)).getUserById(anyInt());
        verify(matchRepository).getMatchByPair(1, 2);
        verify(matchRepository).createMatch(match);

        // Assert the expected result based on the test data
        assertNotNull(result);
        assertEquals(match.getUserId1(), result.getUserId1());
        assertEquals(match.getUserId2(), result.getUserId2());
    }

    @Test
    void testCreateMatchUserNotFound() {
        // Prepare test data
        Match match = Match.builder().userId1(1).userId2(2).build();

        // Mock the behavior of your dependencies
        when(userRepository.getUserById(1)).thenReturn(null);

        // Call the method to be tested and assert the exception
        NotFoundException exception = assertThrows(NotFoundException.class, () -> matchService.createMatch(match));
        assertEquals("User 1 not found", exception.getMessage());

        // Verify the interactions with the mocks
        verify(userRepository).getUserById(1);
        verifyNoInteractions(matchRepository);
    }

    @Test
    void testCreateMatchUser2NotFound() {
        // Prepare test data
        Match match = Match.builder().userId1(1).userId2(2).build();

        // Mock the behavior of your dependencies
        when(userRepository.getUserById(1)).thenReturn(new User(1, "Jane", "Doe", "1992-02-02", "jane.doe@example.com", "9876543210", 2, "test456", 1, 3, "test3", "test3"));
        when(userRepository.getUserById(2)).thenReturn(null);

        // Call the method to be tested and assert the exception
        NotFoundException exception = assertThrows(NotFoundException.class, () -> matchService.createMatch(match));
        assertEquals("User 2 not found", exception.getMessage());

        // Verify the interactions with the mocks
        verify(userRepository).getUserById(2);
        verifyNoInteractions(matchRepository);
    }


    @Test
    void testCreateMatchSameUser() {
        // Prepare test data
        Match match = Match.builder().userId1(1).userId2(1).build();
        when(userRepository.getUserById(1)).thenReturn(new User(1, "Jane", "Doe", "1992-02-02", "jane.doe@example.com", "9876543210", 2, "test456", 1, 3, "test3", "test3"));

        // Call the method to be tested and assert the exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> matchService.createMatch(match));
        assertEquals("Cannot match same user", exception.getMessage());
    }

    @Test
    void testCreateMatchMatchExists() {
        // Prepare test data
        Match match = Match.builder().userId1(1).userId2(2).build();

        // Mock the behavior of your dependencies
        when(userRepository.getUserById(1)).thenReturn(new User(1, "Jane", "Doe", "1992-02-02", "jane.doe@example.com", "9876543210", 2, "test456", 1, 3, "test3", "test3"));
        when(userRepository.getUserById(2)).thenReturn(new User(2, "Jane", "Doe", "1992-02-02", "jane.doe@example.com", "9876543210", 2, "test456", 1, 3, "test3", "test3"));
        when(matchRepository.getMatchByPair(1, 2)).thenReturn(Match.builder().userId1(1).userId2(2).build());

        // Call the method to be tested and assert the exception
        AlreadyExistException exception = assertThrows(AlreadyExistException.class, () -> matchService.createMatch(match));
        assertEquals("Match already exists", exception.getMessage());

        // Verify the interactions with the mocks
        verify(userRepository, times(2)).getUserById(anyInt());
        verify(matchRepository).getMatchByPair(1, 2);
        verifyNoMoreInteractions(matchRepository);
    }

    @Test
    void testGetMatchByPair() {
        // Prepare test data
        int userId1 = 1;
        int userId2 = 2;
        Match expectedMatch = Match.builder().userId1(1).userId2(2).build();

        // Mock the behavior of your dependencies
        when(matchRepository.getMatchByPair(userId1, userId2)).thenReturn(expectedMatch);

        // Call the method to be tested
        Match result = matchService.getMatchByPair(userId1, userId2);

        // Verify the interactions with the mocks
        verify(matchRepository).getMatchByPair(userId1, userId2);

        // Assert the expected result based on the test data
        assertNotNull(result);
        assertEquals(expectedMatch, result);
    }

    @Test
    void testGetMatchesByUserId() {
        // Prepare test data
        int userId = 1;
        Match match1 = Match.builder().userId1(userId).userId2(3).build();
        Match match2 = Match.builder().userId1(3).userId2(userId).build();
        List<Match> matches = Arrays.asList(match1, match2);

        List<User> expectedUsers = List.of(
                new User(1, "Jane", "Doe", "1992-02-02", "jane.doe@example.com", "9876543210", 2, "test456", 1, 3, "test3", "test3"),
                new User(2, "Jane", "Doe", "1992-02-02", "jane.doe@example.com", "9876543210", 2, "test456", 1, 3, "test3", "test3")
        );

        // Mock the behavior of your dependencies
        when(matchRepository.getMatchesByUserId(userId)).thenReturn(matches);

        // Corrected the argument to match the actual call in the method
        when(userRepository.getUsersById(Arrays.asList(3))).thenReturn(expectedUsers);

        // Call the method to be tested
        List<User> result = matchService.getMatchesByUserId(userId);

        // Verify the interactions with the mocks
        verify(matchRepository).getMatchesByUserId(userId);
        verify(userRepository).getUsersById(Arrays.asList(3)); // Corrected the argument

        // Assert the expected result based on the test data
        assertNotNull(result);
        assertEquals(expectedUsers, result);
    }
}