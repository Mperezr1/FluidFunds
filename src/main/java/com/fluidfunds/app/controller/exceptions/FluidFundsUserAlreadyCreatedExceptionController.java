package com.fluidfunds.app.controller.exceptions;

import com.fluidfunds.app.exception.FluidFundsAuthenticationException;
import com.fluidfunds.app.exception.FluidFundsUserAlreadyCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class FluidFundsUserAlreadyCreatedExceptionController {

    @ExceptionHandler(FluidFundsUserAlreadyCreatedException.class)
    public ResponseEntity<String> handleAuthenticationException(FluidFundsUserAlreadyCreatedException ex) {
        return new ResponseEntity<>("User already created: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}

