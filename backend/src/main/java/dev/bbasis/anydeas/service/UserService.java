package dev.bbasis.anydeas.service;

import dev.bbasis.anydeas.exceptions.InvalidUserDetailsException;
import dev.bbasis.anydeas.model.User;
import dev.bbasis.anydeas.repository.UserRepository;
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

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(User user) {
        Objects.requireNonNull(user);

        if (userRepository.existsByEmail(user.getEmail()) || userRepository.existsByUsername(user.getUsername())) {
            throw new InvalidUserDetailsException("Provided user details are already taken.");
        }

        user.encodePassword(passwordEncoder);
        userRepository.save(user);
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


}
