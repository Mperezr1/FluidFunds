package com.fluidfunds.app.controller.exceptions;

import com.fluidfunds.app.exception.FluidFundsAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class FluidFundsAuthenticationExceptionController {

    @ExceptionHandler(FluidFundsAuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(FluidFundsAuthenticationException ex) {
        return new ResponseEntity<>("Auth error: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
