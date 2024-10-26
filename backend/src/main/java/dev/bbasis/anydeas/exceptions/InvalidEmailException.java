package dev.bbasis.anydeas.exceptions;

public class InvalidEmailException extends ValidationException {
    public InvalidEmailException(String message) {
        super(message);
    }
}
