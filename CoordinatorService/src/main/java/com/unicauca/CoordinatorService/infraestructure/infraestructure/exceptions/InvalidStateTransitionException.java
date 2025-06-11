package com.unicauca.CoordinatorService.infraestructure.infraestructure.exceptions;

public class InvalidStateTransitionException extends RuntimeException {
    public InvalidStateTransitionException(String message) {
        super(message);
    }
}
