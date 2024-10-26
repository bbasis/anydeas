package dev.bbasis.anydeas.service.validation;

import dev.bbasis.anydeas.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserValidationInput {
    private String email;
    private String password;
    private String username;
    private String nickname;

    public static UserValidationInput fromUser(User user) {
        UserValidationInput userValidationInput = new UserValidationInput();
        userValidationInput.setEmail(user.getEmail());
        userValidationInput.setPassword(user.getPassword());
        userValidationInput.setUsername(user.getUsername());
        userValidationInput.setNickname(user.getNickname());
        return userValidationInput;
    }
}
