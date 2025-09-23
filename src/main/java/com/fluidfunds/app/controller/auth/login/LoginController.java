package com.fluidfunds.app.controller.auth.login;

import com.fluidfunds.app.dto.auth.login.LoginRequestDTO;
import com.fluidfunds.app.dto.auth.login.LoginResponseDTO;
import com.fluidfunds.app.exception.FluidFundsAuthenticationException;
import com.fluidfunds.app.facade.FluidFundsLoginFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/login")
@AllArgsConstructor
public class LoginController {
    private final FluidFundsLoginFacade fluidFundsLoginFacade;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        try {
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, fluidFundsLoginFacade.loginUserAngGetJWT(request).toString()).build();
        } catch (FluidFundsAuthenticationException fae) {
            throw new FluidFundsAuthenticationException(fae.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}