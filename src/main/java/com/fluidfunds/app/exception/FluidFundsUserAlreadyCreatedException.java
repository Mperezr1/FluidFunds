package com.fluidfunds.app.exception;

public class FluidFundsUserAlreadyCreatedException extends IllegalStateException {
    public FluidFundsUserAlreadyCreatedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public FluidFundsUserAlreadyCreatedException(String msg) {
        super(msg);
    }
}
