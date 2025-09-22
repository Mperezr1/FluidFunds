package com.fluidfunds.app.service.auth;

public interface FluidFundsUserAuthenticationService {

     void authenticateUser(final String userEmail, final String userPassword);

}
