package com.fluidfunds.app.exception;

import org.springframework.security.core.AuthenticationException;


public class FluidFundsAuthenticationException extends AuthenticationException {

    public FluidFundsAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public FluidFundsAuthenticationException(String msg) {
        super(msg);
    }

}
