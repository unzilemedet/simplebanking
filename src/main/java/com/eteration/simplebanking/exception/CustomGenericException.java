package com.eteration.simplebanking.exception;


import lombok.Getter;

// This class is a placeholder you can change the complete implementation
@Getter
public class CustomGenericException extends RuntimeException {
    private final ErrorType errorType;

    public CustomGenericException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }


}
