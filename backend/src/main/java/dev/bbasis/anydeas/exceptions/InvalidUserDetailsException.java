package dev.bbasis.anydeas.exceptions;

public class InvalidUserDetailsException extends ValidationException{
    public InvalidUserDetailsException(String message) {
        super(message);
    }
}
