package dev.bbasis.anydeas.exceptions;

public class InvalidPasswordException extends ValidationException{
    public InvalidPasswordException(String message) {
        super(message);
    }
}
