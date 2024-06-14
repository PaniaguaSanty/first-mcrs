package com.mcrs.personsInput.person.exception;

public class EntityNotFoundException extends ServiceException{
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
