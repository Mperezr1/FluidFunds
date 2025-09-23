package com.fluidfunds.app.facade.impl;

import com.fluidfunds.app.dto.auth.login.LoginRequestDTO;
import com.fluidfunds.app.exception.FluidFundsAuthenticationException;
import com.fluidfunds.app.facade.FluidFundsLoginFacade;
import com.fluidfunds.app.model.UserModel;
import com.fluidfunds.app.service.auth.FluidFundsUserAuthenticationService;
import com.fluidfunds.app.service.jwt.JWTService;
import com.fluidfunds.app.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultFluidFundsLoginFacade implements FluidFundsLoginFacade {

    @Value("${expiration.time.generated.token}")
    private int EXPIRATION_TIME;

    @Value("${expiration.time.generated.token.cookie.seconds}")
    private int EXPIRATION_TIME_COOKIE_SECONDS;

    private final UserService userService;
    private final JWTService jwtService;
    private final FluidFundsUserAuthenticationService fluidFundsUserAuthenticationService;


    @Override
    public ResponseCookie loginUserAngGetJWT(LoginRequestDTO loginRequestDTO) {
        try {
            fluidFundsUserAuthenticationService.authenticateUser(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
            final String generatedToken = jwtService.generateToken(getUserDetailsForAuthenticatedUser(loginRequestDTO.getEmail()), EXPIRATION_TIME);
            return  generateResponseCookieForAuthenticated(generatedToken);
        } catch (FluidFundsAuthenticationException ae) {
            throw new FluidFundsAuthenticationException(ae.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private ResponseCookie generateResponseCookieForAuthenticated(final String generatedToken) {
        return  ResponseCookie.from("accessToken", generatedToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(EXPIRATION_TIME_COOKIE_SECONDS)
                .sameSite("Strict")
                .build();
    }

    private UserDetails getUserDetailsForAuthenticatedUser(final String username) {
        UserModel user = userService.findUserByEmail(username);
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPasswordHash())
                .authorities("USER")
                .build();
    }
}
