package dev.bbasis.anydeas.service.validation;

import dev.bbasis.anydeas.exceptions.ValidationException;

public interface UserInputValidator {

    void validateUserInput(UserValidationInput input) throws ValidationException;
}
