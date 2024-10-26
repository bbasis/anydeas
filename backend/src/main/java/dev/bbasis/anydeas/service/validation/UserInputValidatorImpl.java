package dev.bbasis.anydeas.service.validation;

import dev.bbasis.anydeas.exceptions.InvalidEmailException;
import dev.bbasis.anydeas.exceptions.InvalidPasswordException;
import dev.bbasis.anydeas.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserInputValidatorImpl implements UserInputValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])");

    // Minimum 5 characters
    private static final Pattern PWD_PATTERN = Pattern.compile("^.{5,}$");

    @Override
    public void validateUserInput(UserValidationInput input) throws ValidationException {
        validateEmail(input.getEmail());
        validatePassword(input.getPassword());
    }

    private void validateEmail(String email) throws ValidationException {
        if (email == null || email.isBlank() || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidEmailException("Email does not match the needed format: " + email);
        }
    }

    private void validatePassword(String password) throws ValidationException{
        if (password == null || password.isBlank() || !PWD_PATTERN.matcher(password).matches()) {
            throw new InvalidPasswordException("Password does not match the needed format");
        }
    }
}
