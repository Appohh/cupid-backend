package cupid.main.business.impl;

import cupid.main.config.custom_exceptions.NotFoundException;
import cupid.main.domain.Entity.Swipe;
import cupid.main.domain.Entity.User;
import cupid.main.domain.adapter.SwipeAdapter;
import cupid.main.domain.adapter.UserAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class SwipeServiceImplTest {

    @Mock
    private SwipeAdapter swipeRepository;

    @Mock
    private UserAdapter userRepository;

    private User mockUser;

    @InjectMocks
    private SwipeServiceImpl swipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockUser = Mockito.mock(User.class);
    }

    @Test
    void createSwipe_shouldCreateSwipeSuccessfully() {
        // Mock data
        Swipe mockSwipe = new Swipe();
        mockSwipe.setOrigin_userId(1);
        mockSwipe.setTarget_userId(2);
        mockSwipe.setDirection(1);

        when(userRepository.getUserById(anyInt())).thenReturn(User.builder()
                .id(1)
                .fName("First")
                .lName("Last")
                .birthday("02-05-2001")
                .email("email@email.com")
                .phone("064321412")
                .gender(2)
                .preferenceId(1)
                .locationId(1)
                .pImage("pic.png")
                .bio("Hi this is my bio")
                .build());


        assertDoesNotThrow(() -> {
            Swipe createdSwipe = swipeService.createSwipe(mockSwipe);
        });
    }

    @Test
    void createSwipe_validSwipe_createsSwipe() {
        // Arrange
        Swipe swipe = new Swipe(null, 2, 1, anyInt(), null);

        // Mock userRepository to return a user for both origin and target userIds
        when(userRepository.getUserById(1)).thenReturn(mockUser);
        when(userRepository.getUserById(2)).thenReturn(mockUser);

        // Mock swipeRepository to return a swipe when createSwipe is called
        when(swipeRepository.createSwipe(any(Swipe.class))).thenReturn(swipe);

        // Act
        Swipe createdSwipe = swipeService.createSwipe(swipe);

        // Assert
        // Add assertions based on your requirements
        verify(swipeRepository, times(1)).createSwipe(any(Swipe.class));
    }

    @Test
    void createSwipe_invalidOriginUser_throwsNotFoundException() {
        // Arrange
        Swipe swipe = new Swipe(null, 2, 1, anyInt(), null);

        // Mock userRepository to return null for origin userId
        when(userRepository.getUserById(1)).thenReturn(null);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> swipeService.createSwipe(swipe));
    }

    @Test
    void createSwipe_invalidTargetUser_throwsNotFoundException() {
        // Arrange
        Swipe swipe = new Swipe(null, 2, 3, anyInt(), null);

        // Mock userRepository to return null for origin userId
        when(userRepository.getUserById(2)).thenReturn(mockUser);
        when(userRepository.getUserById(3)).thenReturn(null);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> swipeService.createSwipe(swipe));
    }

    @Test
    void getSwipesByUserId_validUserId_returnsListOfSwipes() {
        // Arrange
        int userId = 1;

        // Mock userRepository to return a user for the given userId
        when(userRepository.getUserById(userId)).thenReturn(mockUser);

        // Mock swipeRepository to return a list of swipes when getSwipesByUserId is called
        List<Swipe> mockSwipes = Collections.singletonList(new Swipe());
        when(swipeRepository.getSwipesByUserId(userId)).thenReturn(mockSwipes);

        // Act
        List<Swipe> result = swipeService.getSwipesByUserId(userId);

        // Assert
        // Add assertions based on your requirements
        assertEquals(mockSwipes, result);
    }

    @Test
    void checkMatch_validPair_returnsTrue() {
        // Arrange
        int origin = 1;
        int target = 2;

        // Mock swipeRepository to return Optional with a swipe for both pairs
        when(swipeRepository.getSwipeRightByPair(origin, target))
                .thenReturn(new Swipe(1, origin, target, 1, "SomeString"));
        when(swipeRepository.getSwipeRightByPair(target, origin))
                .thenReturn(new Swipe(2, origin, target, 2, "AnotherString"));

        // Act
        boolean result = swipeService.checkMatch(origin, target);

        // Assert
        assertTrue(result);
    }

    @Test
    void checkMatch_noMatch_returnsFalse() {
        // Arrange
        int origin = 1;
        int target = 2;

        // Mock swipeRepository to return null for both pairs
        when(swipeRepository.getSwipeRightByPair(origin, target)).thenReturn(null);
        when(swipeRepository.getSwipeRightByPair(target, origin)).thenReturn(null);

        // Act
        boolean result = swipeService.checkMatch(origin, target);

        // Assert
        assertFalse(result);
    }


    @Test
    void createSwipe_swipingOwnAccount_throwsIllegalArgumentException() {
        // Arrange
        Swipe swipe = new Swipe();
        swipe.setOrigin_userId(1);
        swipe.setTarget_userId(1);
        swipe.setDirection(1);

        when(userRepository.getUserById(1)).thenReturn(mockUser);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> swipeService.createSwipe(swipe));
    }

    @Test
    void deleteSwipeById_validId_deletesSwipe() {
        // Arrange
        int swipeId = 1;

        // Act
        swipeService.deleteSwipeById(swipeId);

        // Assert
        verify(swipeRepository, times(1)).deleteSwipeById(eq(swipeId));
    }

    @Test
    void getSwipesByUserId_invalidUserId_throwsNotFoundException() {
        // Arrange
        int userId = 1;

        // Mock userRepository to return null for the given userId
        when(userRepository.getUserById(userId)).thenReturn(null);

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> swipeService.getSwipesByUserId(userId));
        assertEquals("User does not exist", exception.getMessage());
    }


}
