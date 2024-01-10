package cupid.main.business.impl;

import cupid.main.domain.Entity.Swipe;
import cupid.main.domain.Entity.User;
import cupid.main.domain.adapter.SwipeAdapter;
import cupid.main.domain.adapter.UserAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class SwipeServiceImplTest {

    @Mock
    private SwipeAdapter swipeRepository;

    @Mock
    private UserAdapter userRepository;

    @InjectMocks
    private SwipeServiceImpl swipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
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
}

//    @Test
//    void createSwipe_shouldThrowNotFoundExceptionWhenUserNotFound() {
//        // Mock data
//        Swipe mockSwipe = new Swipe(/* Add necessary properties */);
//
//        // Stubbing behavior for userRepository.getUserById() to return null
//        when(userRepository.getUserById(anyLong())).thenReturn(null);
//
//        // Behavior verification
//        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
//            swipeService.createSwipe(mockSwipe);
//        });
//
//        // Verify that getUserById was called twice (for origin and target user) with correct arguments
//        verify(userRepository, times(2)).getUserById(anyLong());
//
//        // Verify that createSwipe was not called
//        verify(swipeRepository, never()).createSwipe(any());
//
//        // Assert the exception message if necessary
//        assertTrue(exception.getMessage().contains("user not found"));
//    }
//
//    // More test cases for other scenarios can be added similarly
//}