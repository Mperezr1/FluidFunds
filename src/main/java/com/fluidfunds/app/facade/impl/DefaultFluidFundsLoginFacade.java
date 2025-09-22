package com.fluidfunds.app.facade.impl;

import com.fluidfunds.app.dto.login.LoginRequestDTO;
import com.fluidfunds.app.dto.login.LoginResponseDTO;
import com.fluidfunds.app.exception.FluidFundsAuthenticationException;
import com.fluidfunds.app.facade.FluidFundsLoginFacade;
import com.fluidfunds.app.model.UserModel;
import com.fluidfunds.app.service.auth.FluidFundsUserAuthenticationService;
import com.fluidfunds.app.service.jwt.JWTService;
import com.fluidfunds.app.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DefaultFluidFundsLoginFacade implements FluidFundsLoginFacade {

    private final UserService userService;
    private final JWTService jwtService;
    private final FluidFundsUserAuthenticationService fluidFundsUserAuthenticationService;


    @Override
    public LoginResponseDTO loginUserAngGetJWT(LoginRequestDTO loginRequestDTO) {
        try {
            fluidFundsUserAuthenticationService.authenticateUser(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
            final String generatedToken = jwtService.generateToken(getUserDetailsForAuthenticatedUser(loginRequestDTO.getEmail()));
            return new LoginResponseDTO(generatedToken);
        } catch (FluidFundsAuthenticationException ae) {
            throw new FluidFundsAuthenticationException(ae.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
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
