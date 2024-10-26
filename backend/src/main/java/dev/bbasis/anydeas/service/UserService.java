package dev.bbasis.anydeas.service;

import dev.bbasis.anydeas.exceptions.DuplicateException;
import dev.bbasis.anydeas.exceptions.InvalidUserDetailsException;
import dev.bbasis.anydeas.exceptions.NotFoundException;
import dev.bbasis.anydeas.exceptions.ValidationException;
import dev.bbasis.anydeas.model.User;
import dev.bbasis.anydeas.repository.UserRepository;
import dev.bbasis.anydeas.service.validation.UserInputValidator;
import dev.bbasis.anydeas.service.validation.UserValidationInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserInputValidator userInputValidator;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserInputValidator userInputValidator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userInputValidator = userInputValidator;
    }

    @Transactional
    public void register(User user) {
        Objects.requireNonNull(user);

        UserValidationInput input = UserValidationInput.fromUser(user);
        validateUserInput(input);

        user.encodePassword(passwordEncoder);
        userRepository.saveAndFlush(user);
    }

    @Transactional
    public User findByUsername(String username) {
        Objects.requireNonNull(username);
        Optional<User> userOpt = userRepository.findByUsername(username);

        return userOpt.orElse(null);
    }

    @Transactional
    public User findById(Integer id) {
        Objects.requireNonNull(id);
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.orElse(null);
    }

    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUser(Integer id) {
        Objects.requireNonNull(id);

        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()){
            throw new NotFoundException("User not found");
        }

        User foundUser = userOpt.get();
        foundUser.setDeleted(true);

        userRepository.saveAndFlush(foundUser);
    }

    private void validateUserInput(UserValidationInput user) throws ValidationException, DuplicateException {
        if (userRepository.existsByUsername(user.getUsername())){
            throw new DuplicateException("Username is already taken: " + user.getUsername());
        }

        if (userRepository.existsByEmail(user.getEmail())){
            throw new DuplicateException("Email is already taken: " + user.getEmail());
        }

        if (userRepository.existsByNickname(user.getNickname())){
            throw new DuplicateException("Nickname is already taken: " + user.getNickname());
        }

        userInputValidator.validateUserInput(user);
    }


}
