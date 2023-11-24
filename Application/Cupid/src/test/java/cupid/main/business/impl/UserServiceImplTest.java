package cupid.main.business.impl;

import cupid.main.domain.Dto.User.CreateUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

public class UserServiceImplTest {
    /**
     * @verifies return created user
     * @see UserServiceImpl#createUser(CreateUser)
     */
//    @Test
//    public void createUser_shouldReturnCreatedUser() throws Exception {
//        // Arrange
//        UserAdapter testUserRepo = mock(UserAdapter.class);
//        when(testUserRepo.createUser(any(), any())).thenAnswer(
//                i -> {
//                    CreateUser user = (CreateUser) i.getArgument(0);
//                    Integer preferenceId = i.getArgument(1);
//                    return User.builder()
//                            .id(1)
//                            .fName(user.getFName())
//                            .lName(user.getLName())
//                            .birthday(user.getBirthday())
//                            .email(user.getEmail())
//                            .phone(user.getPhone())
//                            .gender(user.getGender())
//                            .preferenceId(preferenceId)
//                            .locationId(user.getLocationId())
//                            .pImage(user.getPImage())
//                            .bio(user.getBio())
//                            .build();
//                }
//        );
//
//        PreferenceAdapter testPreferenceRepo = mock(PreferenceAdapter.class);
//        when(testPreferenceRepo.createPreference()).thenAnswer(
//                i -> Preference.builder()
//                        .id(1)
//                        .gender(1)
//                        .bodyType(1)
//                        .distance(1)
//                        .age(1)
//                        .height(1)
//                        .ethnicity(1)
//                        .build()
//        );
//
//
//                CreateUser request = CreateUser.builder()
//                .fName("John")
//                .lName("Doe")
//                .birthday("1990-01-01")
//                .email("john.doe@example.com")
//                .phone("1234567890")
//                .gender(1)
//                .locationId(789)
//                .pImage("profile_image_url.jpg")
//                .bio("This is a mock bio.")
//                .build();
//
//
//        AccessTokenEncoderDecoderImpl testTokenize = mock(AccessTokenEncoderDecoderImpl.class);
//
//
//        UserServiceImpl sut = new UserServiceImpl(testUserRepo, testPreferenceRepo, );
//        // Act
//
//        User sutResponse = sut.createUser(request);
//
//        // Assert
//
//        Assert.notNull(sutResponse.getId(), "Id shouldn't be null");
//
//    }

    /**
     * @verifies throw Alreadyexistexception if user email already exist
     * @see UserServiceImpl#createUser(CreateUser)
     */
//    @Test
//    public void createUser_shouldThrowAlreadyexistexceptionIfUserEmailAlreadyExist() throws Exception {
//        //TODO auto-generated
//        Assertions.fail("Not yet implemented");
//    }

    /**
     * @verifies throw Alreadyexistexception if user phone already exist
     * @see UserServiceImpl#createUser(CreateUser)
     */
//    @Test
//    public void createUser_shouldThrowAlreadyexistexceptionIfUserPhoneAlreadyExist() throws Exception {
//        //TODO auto-generated
//        Assertions.fail("Not yet implemented");
//    }

    /**
     * @verifies throw notfoundexception if the user is not found
     * @see UserServiceImpl#getUserById(Integer)
     */
//    @Test
//    public void getUserById_shouldThrowNotfoundexceptionIfTheUserIsNotFound() throws Exception {
//        // Arrange
//        UserAdapter mockUserRepo = mock(UserAdapter.class);
//        PreferenceAdapter mockPreferenceRepo = mock(PreferenceAdapter.class);
//
//        int userId = 1;
//
//        // Mock the behavior of userRepository.getUserById to return the user
//        when(mockUserRepo.getUserById(userId)).thenThrow(NotFoundException.class);
//
//        UserService sut = new UserServiceImpl(mockUserRepo, mockPreferenceRepo);
//        // Act
//
//        // Assert
//        Assertions.assertThrows(NotFoundException.class, () -> {
//            sut.getUserById(userId);
//        });
//
//    }

    /**
     * @verifies return the user with the appropriate id
     * @see UserServiceImpl#getUserById(Integer)
     */
//    @Test
//    public void getUserById_shouldReturnTheUserWithTheAppropriateId() throws Exception {
//        // Arrange
//        UserAdapter mockUserRepo = mock(UserAdapter.class);
//        PreferenceAdapter mockPreferenceRepo = mock(PreferenceAdapter.class);
//
//        int userId = 1;
//        User existingUser = User.builder()
//                .id(userId)
//                .fName("John")
//                .lName("Doe")
//                .birthday("1990-01-01")
//                .email("john.doe@example.com")
//                .phone("1234567890")
//                .gender(1)
//                .preferenceId(456)
//                .locationId(789)
//                .pImage("profile_image_url.jpg")
//                .bio("This is a mock bio.")
//                .build();
//        // Mock the behavior of userRepository.getUserById to return the user
//        when(mockUserRepo.getUserById(userId)).thenReturn(existingUser);
//
//        UserService sut = new UserServiceImpl(mockUserRepo, mockPreferenceRepo);
//        // Act
//        User result = sut.getUserById(userId);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(existingUser, result);
//    }
}
