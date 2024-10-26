package dev.bbasis.anydeas.service;

import dev.bbasis.anydeas.environment.Generator;
import dev.bbasis.anydeas.exceptions.DuplicateException;
import dev.bbasis.anydeas.exceptions.ValidationException;
import dev.bbasis.anydeas.model.User;
import dev.bbasis.anydeas.repository.UserRepository;
import dev.bbasis.anydeas.service.validation.UserInputValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Mock
    private UserRepository userRepositoryMock;

    private UserService userServiceMock;

    @Autowired
    private UserInputValidator userValidator;

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceTest.class);

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void userPersistedCorrectly(){
        userServiceMock = new UserService(userRepositoryMock, passwordEncoder, userValidator);

        User user = Generator.generateUser();

        userServiceMock.register(user);

        final ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepositoryMock).save(captor.capture());

        LOG.info(captor.getValue().toString());
    }

    @Test
    public void userValidationThrowsExceptionForInvalidEmail(){
        userService = new UserService(userRepository, passwordEncoder, userValidator);
        User user = Generator.generateUser();
        user.setEmail("notanEmail.com");

        assertThrows(ValidationException.class, () -> {
            userService.register(user);
        });
    }

    @Test
    public void userValidationThrowsExceptionForInvalidPassword(){
        userService = new UserService(userRepository, passwordEncoder, userValidator);

        String shortPassword = "abc";

        User user = Generator.generateUser();
        user.setPassword(shortPassword);

        assertThrows(ValidationException.class, () -> {
            userService.register(user);
        });
    }

    @Test
    public void userValidationThrowsExceptionForDuplicateCredentials(){
        userService = new UserService(userRepository, passwordEncoder, userValidator);

        String email = "username@email.com";
        String username = "username10";
        String nickname = "nickname10";

        User originalUser = Generator.generateUser();
        originalUser.setEmail(email);
        originalUser.setUsername(username);
        originalUser.setNickname(nickname);

        userService.register(originalUser);

        User userWithDuplicateEmail = Generator.generateUser();
        userWithDuplicateEmail.setEmail(email);

        User userWithDuplicateUsername = Generator.generateUser();
        userWithDuplicateUsername.setUsername(username);

        User userWithDuplicateNickname = Generator.generateUser();
        userWithDuplicateNickname.setNickname(nickname);


        DuplicateException emailException = assertThrows(DuplicateException.class, () -> {
            userService.register(userWithDuplicateEmail);
        });

        assertTrue(emailException.getMessage().contains(email));

        DuplicateException usernameException = assertThrows(DuplicateException.class, () -> {
            userService.register(userWithDuplicateUsername);
        });

        assertTrue(usernameException.getMessage().contains(username));

        DuplicateException nicknameException = assertThrows(DuplicateException.class, () -> {
            userService.register(userWithDuplicateNickname);
        });

        assertTrue(nicknameException.getMessage().contains(nickname));
    }

    @Test
    public void userDeleteMarksUserAsDeleted(){
        userService = new UserService(userRepository, passwordEncoder, userValidator);

        User user = Generator.generateUser();

        userService.register(user);

        User found = userService.findById(user.getId());

        assertNotNull(found);

        assertFalse(found.isDeleted());

        userService.deleteUser(found.getId());

        User deleted = userService.findById(user.getId());

        assertTrue(deleted.isDeleted());

        LOG.info(deleted.toString());
    }

    @Test
    public void userRetrievedCorrectly(){
        userService = new UserService(userRepository, passwordEncoder, userValidator);

        User user = Generator.generateUser();

        user.setNickname("NICKNAME UGA BOOGA");
        userService.register(user);

        User retrievedUser = userService.findByUsername(user.getUsername());

        assertNotNull(retrievedUser);

        assertEquals(retrievedUser.getNickname(), "NICKNAME UGA BOOGA");

        LOG.info(user.toString());
    }

}
