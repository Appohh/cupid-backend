package cupid.main.business.impl;

import cupid.main.business.service.UserService;
import cupid.main.config.Security;
import cupid.main.config.custom_exceptions.AlreadyExistException;
import cupid.main.config.custom_exceptions.NotFoundException;
import cupid.main.config.custom_exceptions.UnAuthorizedException;
import cupid.main.config.security.token.AccessToken;
import cupid.main.config.security.token.impl.AccessTokenEncoderDecoderImpl;
import cupid.main.config.security.token.impl.AccessTokenImpl;
import cupid.main.domain.Dto.Appearance.UpdateAppearance;
import cupid.main.domain.Dto.Preference.UpdatePreference;
import cupid.main.domain.Dto.Role.CreateRole;
import cupid.main.domain.Dto.User.CreateUser;
import cupid.main.domain.Dto.User.UserLogin;
import cupid.main.domain.Entity.Appearance;
import cupid.main.domain.Entity.Preference;
import cupid.main.domain.Entity.Role;
import cupid.main.domain.Entity.User;
import cupid.main.domain.adapter.*;
import cupid.main.domain.other.ImageService;
import cupid.main.persistence.mysql.MySQLUserRepository;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    AccessTokenEncoderDecoderImpl tokenEncoderDecoder;
    @Mock
    AccessToken accessToken;
    @Mock
    UserAdapter userAdapter;
    @Mock
    PreferenceAdapter preferenceAdapter;
    @Mock
    RoleAdapter roleAdapter;
    @Mock
    AppearanceAdapter appearanceAdapter;
    @Mock
    ReferralAdapter referralAdapter;
    @Mock
    ImageService imageService;

    @InjectMocks
    UserServiceImpl userService;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_shouldReturnCreatedUser() {
        // Arrange
        CreateUser createUser = new CreateUser("t", "g", "1999-01-01", "ja@kf.com", "061234567", 1, "test123", 1, "test.png", "test", 1);
        User userMock = new User (1, "t", "g", "1999-01-01", "ja@kf.com", "061234567", 1, "test123", 1, 1, "test", "test");


        when(userAdapter.userExist(any(), any())).thenReturn(false);
        when(preferenceAdapter.createPreference()).thenReturn(new Preference(1, 1, 18,5, 1, 4));
        when(appearanceAdapter.createAppearance(any())).thenReturn(new Appearance(1, 2, 1, "test", 1, 4));
        when(imageService.SaveImage(any())).thenReturn("image.pdf");
        when(userAdapter.createUser(createUser, 1)).thenReturn(userMock);
        when(roleAdapter.createRole(new CreateRole(1, 1))).thenReturn(new Role(1, 1, 1));
        when(userAdapter.userExist(("ja@kf.com"), ("061234567"))).thenReturn(false);

        // Act
        User result = userService.createUser(createUser);

        // Assert

        verify(userAdapter, times(1)).userExist(any(), any());
        verify(preferenceAdapter, times(1)).createPreference();
        verify(imageService, times(1)).SaveImage(any());
        verify(userAdapter, times(1)).createUser(any(), anyInt());
        verify(appearanceAdapter, times(1)).createAppearance(any());
        verify(roleAdapter, times(1)).createRole(any());
    }


    /**
     * @verifies throw Alreadyexistexception if user email already exist
     * @see UserServiceImpl#createUser(CreateUser)
     */
    @Test
    void createUser_shouldThrowAlreadyExistExceptionIfUserEmailAlreadyExist() throws Exception {
        // Arrange
        CreateUser createUser = new CreateUser("t", "g", "1999-01-01", "ja@kf.com", "061234567", 1, "test123", 1, "test.png", "test", 1);

        // Mocking behavior to make userExist return true
        when(userAdapter.userExist(("ja@kf.com"), ("061234567"))).thenReturn(true);

        // Act and Assert
        assertThrows(AlreadyExistException.class, () -> userService.createUser(createUser));

        // Verify interactions
        verify(userAdapter, times(1)).userExist(any(), any());
        verifyNoMoreInteractions(userAdapter, preferenceAdapter, imageService, userAdapter, appearanceAdapter, roleAdapter);
    }

    /**
     * @verifies return the user with the appropriate id
     * @see UserServiceImpl#getUserById(Integer)
     */
    @Test
    void getUserById_shouldReturnTheUserWithTheAppropriateId() throws Exception {
        // Arrange
        PreferenceAdapter mockPreferenceRepo = mock(PreferenceAdapter.class);

        int userId = 1;
        User existingUser = User.builder()
                .id(userId)
                .fName("John")
                .lName("Doe")
                .birthday("1990-01-01")
                .email("john.doe@example.com")
                .phone("1234567890")
                .gender(1)
                .preferenceId(456)
                .locationId(789)
                .pImage("profile_image_url.jpg")
                .bio("This is a mock bio.")
                .build();
        // Mock the behavior of userRepository.getUserById to return the user
        when(userAdapter.getUserById(userId)).thenReturn(existingUser);

        UserService sut = userService;
        // Act
        User result = sut.getUserById(userId);

        // Assert
        assertNotNull(result);
        assertEquals(existingUser, result);
    }

    @Test
    void testValidateReferralCode_ValidCode_ReturnsUserId() {
        // Arrange
        String validReferralCode = "validCode";
        Integer expectedUserId = 123;

        // Mock the behavior of referralRepository.validateRefferalCode
        when(referralAdapter.validateRefferalCode(validReferralCode)).thenReturn(expectedUserId);

        // Act
        Integer actualUserId = userService.validateReferralCode(validReferralCode);

        // Assert
        assertEquals(expectedUserId, actualUserId);
        // Optionally, verify that the method was called with the correct argument
        verify(referralAdapter, times(1)).validateRefferalCode(validReferralCode);
    }

    @Test
    void testUpdateAppearance_ValidUpdate_ReturnsUpdatedAppearance() {
        // Arrange
        UpdateAppearance updateAppearance = new UpdateAppearance(1, 2, "City", 1, 2);

        // Mock the behavior of appearanceRepository.updateAppearance
        Appearance updatedAppearance = Appearance.builder()
                .userId(updateAppearance.getUserId())
                .gender(updateAppearance.getGender())
                .bodyType(updateAppearance.getBodyType())
                .location(updateAppearance.getLocation())
                .ethnicity(updateAppearance.getEthnicity())
                .build();

        when(appearanceAdapter.updateAppearance(updatedAppearance)).thenReturn(updatedAppearance);

        // Act
        Appearance result = userService.updateAppearance(updateAppearance);

        // Assert
        assertNotNull(result);
        assertEquals(updatedAppearance, result);
        // Optionally, verify that the method was called with the correct argument
        verify(appearanceAdapter, times(1)).updateAppearance(updatedAppearance);
    }

    @Test
    void testUserFilledAppearance_AppearanceFilled_ReturnsTrue() {
        // Arrange
        User user = new User(1, "John", "Doe", "1990-01-01", "john.doe@example.com", "1234567890", 1, "test123", 1, 1, "test", "test");
        Appearance filledAppearance = Appearance.builder()
                .userId(user.getId())
                .gender(1)
                .bodyType(1)
                .location("City")
                .ethnicity(2)
                .build();

        // Mock the behavior of appearanceRepository.getAppearanceByUserId
        when(appearanceAdapter.getAppearanceByUserId(user.getId())).thenReturn(filledAppearance);
        // Mock the behavior of appearanceRepository.appearanceFilled
        when(appearanceAdapter.appearanceFilled(filledAppearance)).thenReturn(true);

        // Act
        boolean result = userService.userFilledAppearance(user);

        // Assert
        assertTrue(result);
        // Optionally, verify that the method was called with the correct argument
        verify(appearanceAdapter, times(1)).getAppearanceByUserId(user.getId());
        verify(appearanceAdapter, times(1)).appearanceFilled(filledAppearance);
    }

    @Test
    void testCreateAppearance_ValidUser_ReturnsCreatedAppearance() {
        // Arrange
        User user = new User(1, "John", "Doe", "1990-01-01", "john.doe@example.com", "1234567890", 1, "test123", 1, 1, "test", "test");

        // Mock the behavior of appearanceRepository.createAppearance
        Appearance createdAppearance = Appearance.builder()
                .userId(user.getId())
                .gender(1)
                .bodyType(2)
                .location("City")
                .ethnicity(1)
                .build();

        when(appearanceAdapter.createAppearance(user)).thenReturn(createdAppearance);

        // Act
        Appearance result = userService.createAppearance(user);

        // Assert
        assertNotNull(result);
        assertEquals(createdAppearance, result);
        // Optionally, verify that the method was called with the correct argument
        verify(appearanceAdapter, times(1)).createAppearance(user);
    }

    @Test
    void testGetUserPreference_ValidUser_ReturnsUserPreference() {
        // Arrange
        User user = new User(1, "John", "Doe", "1990-01-01", "john.doe@example.com", "1234567890", 1, "test123", 1, 1, "test", "test");

        // Mock the behavior of preferenceRepository.getPreferenceById
        Preference userPreference = new Preference(1, 1, 18, 5, 1, 4);

        when(preferenceAdapter.getPreferenceById(user.getPreferenceId())).thenReturn(userPreference);

        // Act
        Preference result = userService.getUserPreference(user);

        // Assert
        assertNotNull(result);
        assertEquals(userPreference, result);
        // Optionally, verify that the method was called with the correct argument
        verify(preferenceAdapter, times(1)).getPreferenceById(user.getPreferenceId());
    }

    @Test
    void testUserFilledPreference_PreferenceFilled_ReturnsTrue() {
        // Arrange
        User user = new User(1, "John", "Doe", "1990-01-01", "john.doe@example.com", "1234567890", 1, "test123", 1, 1, "test", "test");

        // Mock the behavior of preferenceRepository.getPreferenceById
        Preference userPreference = new Preference(1, 1, 18, 5, 1, 4);

        when(preferenceAdapter.getPreferenceById(user.getPreferenceId())).thenReturn(userPreference);
        // Mock the behavior of preferenceRepository.PreferenceFilled
        when(preferenceAdapter.PreferenceFilled(userPreference)).thenReturn(true);

        // Act
        boolean result = userService.userFilledPreference(user);

        // Assert
        assertTrue(result);
        // Optionally, verify that the method was called with the correct argument
        verify(preferenceAdapter, times(1)).getPreferenceById(user.getPreferenceId());
        verify(preferenceAdapter, times(1)).PreferenceFilled(userPreference);
    }

    @Test
    void testUserFilledPreference_UserHasNoPreferenceId_ThrowsIllegalArgumentException() {
        // Arrange
        User user = new User(3, "Alice", "Wonderland", "1998-03-20", "alice.wonderland@example.com", "1239874560", 1, "test789", null, 3, "test3", "test3");

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.userFilledPreference(user));
        assertEquals("User has no preference id", exception.getMessage());

        // Verify that the preferenceRepository methods were not called
        verifyNoInteractions(userAdapter);
    }

    @Test
    void testUpdateUserPreference() {
        // Arrange
        User user = new User();
        user.setPreferenceId(1);

        UpdatePreference updatePreference = new UpdatePreference(1, 25 ,10,  1, 1);

//        UpdatePreference expectedPreference = UpdatePreference.builder()
//                .gender(updatePreference.getGender())
//                .bodyType(updatePreference.getBodyType())
//                .distance(updatePreference.getDistance())
//                .age(updatePreference.getAge())
//                .ethnicity(updatePreference.getEthnicity())
//                .build();

        when(preferenceAdapter.UpdatePreference(any(Preference.class))).thenReturn(any(Preference.class));

        when(userService.updateUserPreference(user, updatePreference)).thenReturn(any(Preference.class));

        // Act
        Preference result = userService.updateUserPreference(user, updatePreference);

        // Assert
        assertNotNull(result); // Assuming the result should not be null
        assertEquals(updatePreference.getGender(), result.getGender());
        assertEquals(updatePreference.getAge(), result.getAge());
        assertEquals(updatePreference.getDistance(), result.getDistance());
        assertEquals(updatePreference.getBodyType(), result.getBodyType());
        assertEquals(updatePreference.getEthnicity(), result.getEthnicity());
    }

    @Test
    void testAuthenticateUser_UserNotFound() {
        // Arrange
        UserLogin attempt = new UserLogin("nonexistent@example.com", "password");

        // Stubbing for the first if condition
        when(userAdapter.userExist(attempt.getEmail(), "")).thenReturn(false);

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.authenticateUser(attempt));
        assertNotNull(exception.getMessage());
    }

    @Test
    void testAuthenticateUser_InvalidPassword() {
        // Arrange
        UserLogin attempt = new UserLogin("test@example.com", "wrongPassword");

        // Stubbing for the first if condition
        when(userAdapter.userExist(attempt.getEmail(), "")).thenReturn(true);

        // Stubbing for the second if condition
        when(userAdapter.getUserHashAndSalt(attempt.getEmail())).thenReturn(Security.hashPassword("correctPassword"));

        // Act & Assert
        UnAuthorizedException exception = assertThrows(UnAuthorizedException.class, () -> userService.authenticateUser(attempt));
        assertNotNull(exception.getMessage());
    }

    @Test
    void testAuthenticateUser() {
        // Arrange
        UserLogin attempt = new UserLogin("test@example.com", "password");
        User loggedUser = new User(1, "Alice", "Wonderland", "1998-03-20", "alice.wonderland@example.com", "1239874560", 1, "test789", null, 3, "test3", "test3");
        Security securityMock = mock(Security.class);

        when(userAdapter.userExist(attempt.getEmail(), "")).thenReturn(true);
        when(userAdapter.getUserHashAndSalt(attempt.getEmail())).thenReturn(Security.hashPassword(attempt.getPassword()));
        when(userAdapter.getUserByEmail(attempt.getEmail())).thenReturn(loggedUser);

        AccessToken jwt = new AccessTokenImpl(loggedUser.getEmail(), loggedUser.getId(), List.of());
        when(tokenEncoderDecoder.encode(any())).thenReturn("encodedToken"); // Use any() matcher

        // Act
        String result = userService.authenticateUser(attempt);

        // Assert
        assertNotNull(result);
    }
}

