package com.fluidfunds.app.service.auth.impl;

import com.fluidfunds.app.exception.FluidFundsAuthenticationException;
import com.fluidfunds.app.service.auth.FluidFundsUserAuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultFluidFundsAuthenticationService implements FluidFundsUserAuthenticationService {

    private final AuthenticationManager authenticationManager;

    @Override
    public void authenticateUser(String userEmail, String userPassword) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userEmail,
                            userPassword
                    )
            );
        } catch (UsernameNotFoundException ufe) {
            throw new FluidFundsAuthenticationException(ufe.getMessage());
        } catch (AuthenticationException ae) {
            throw new FluidFundsAuthenticationException("Authentication failed for user: " + userEmail) {
            };
        }
    }
}
