package com.eteration.simplebanking.exception;

import com.eteration.simplebanking.controller.TransactionStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.ArrayList;
import java.util.List;

//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(com.eteration.simplebanking.model.InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<TransactionStatus> handleCustomException(com.eteration.simplebanking.model.InsufficientBalanceException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TransactionStatus("ERROR - InsufficientBalance", "none"));
    }
    @ExceptionHandler(CustomGenericException.class)
    public ResponseEntity<ErrorMessage> handleManagerException(CustomGenericException ex){
        ErrorType errorType=ex.getErrorType();
        HttpStatus httpStatus=errorType.httpStatus;
        return new ResponseEntity<>(createError(errorType,ex),httpStatus);
    }

    private ErrorMessage createError(ErrorType errorType, Exception ex) {
        System.out.println("Hata olustu: "+ex.getMessage());
        return ErrorMessage.builder()
                .code(errorType.getCode())
                .message(errorType.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)

    public final ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        ErrorType errorType=ErrorType.BAD_REQUEST;
        List<String> fields=new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(e->fields.add(e.getField()+": "+e.getDefaultMessage()));
        ErrorMessage errorMessage=createError(errorType,exception);
        errorMessage.setFields(fields);
        return new ResponseEntity<>(errorMessage,errorType.getHttpStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)

    public final ResponseEntity<ErrorMessage> handleMessageNotReadableException(
            HttpMessageNotReadableException exception) {
        ErrorType errorType = ErrorType.BAD_REQUEST;
        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }



}