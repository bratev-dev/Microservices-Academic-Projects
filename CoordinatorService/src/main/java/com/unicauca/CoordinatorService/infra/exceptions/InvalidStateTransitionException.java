package com.unicauca.CoordinatorService.infra.exceptions;

public class InvalidStateTransitionException extends RuntimeException {
    public InvalidStateTransitionException(String message) {
        super(message);
    }
}
