package com.incedo.capstone.smartinventory.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<String> handlingException(UserCreationException uce)
    {
        ResponseEntity<String> re = new ResponseEntity<>(uce.getMessage(), HttpStatus.BAD_REQUEST);
        return re;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handlingException(UserNotFoundException unfe)
    {
        ResponseEntity<String> re = new ResponseEntity<>(unfe.getMessage(), HttpStatus.BAD_REQUEST);
        return re;
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<String> handlingException(IncorrectPasswordException ipe)
    {
        ResponseEntity<String> re = new ResponseEntity<>(ipe.getMessage(), HttpStatus.BAD_REQUEST);
        return re;
    }
}
