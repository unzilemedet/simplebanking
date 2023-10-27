package com.eteration.simplebanking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    INSUFFICIENT_CURRENCY(1000,"Insufficient Currency",HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_FOUND(1001,"Account not found", HttpStatus.NOT_FOUND),

    ERROR(9000,"ERROR",HttpStatus.INTERNAL_SERVER_ERROR),
    INTERNAL_ERROR(5100,"Eternal Error",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4001,"Parameter Error",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(8001,"User not found",HttpStatus.NOT_FOUND),


    ;


    private int code;
    private String message;
    HttpStatus httpStatus;
}