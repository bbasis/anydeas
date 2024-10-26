package dev.bbasis.anydeas.exceptions;

public class InvalidUserDetailsException extends RuntimeException{
    public InvalidUserDetailsException(String message) {
        super(message);
    }
}
