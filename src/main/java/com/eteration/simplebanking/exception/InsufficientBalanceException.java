package com.eteration.simplebanking.exception;


import lombok.Getter;

// This class is a placeholder you can change the complete implementation
@Getter
public class InsufficientBalanceException extends RuntimeException {
    private final ErrorType errorType;

    public InsufficientBalanceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }


}
