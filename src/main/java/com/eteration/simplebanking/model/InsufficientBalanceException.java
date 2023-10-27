package com.eteration.simplebanking.model;


import com.eteration.simplebanking.exception.ErrorType;
import lombok.Getter;

// This class is a place holder you can change the complete implementation
@Getter
public class InsufficientBalanceException extends Exception {
    private final ErrorType errorType;

    public InsufficientBalanceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
