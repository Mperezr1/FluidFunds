package com.fluidfunds.app.controller;

import com.fluidfunds.app.dto.login.LoginRequestDTO;
import com.fluidfunds.app.dto.login.LoginResponseDTO;
import com.fluidfunds.app.dto.register.RegisterRequestDTO;
import com.fluidfunds.app.dto.register.RegisterResponseDTO;
import com.fluidfunds.app.model.UserModel;
import com.fluidfunds.app.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        UserModel registeredUser = authService.registerUser(request);

        RegisterResponseDTO response = RegisterResponseDTO.builder()
                .id(registeredUser.getId())
                .email(registeredUser.getEmail())
                .accountTier(registeredUser.getAccountTier())
                .createdAt(registeredUser.getCreatedAt())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        String token = authService.loginUser(request);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}