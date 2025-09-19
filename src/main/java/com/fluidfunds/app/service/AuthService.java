package com.fluidfunds.app.service;

import com.fluidfunds.app.dto.login.LoginRequestDTO;
import com.fluidfunds.app.dto.register.RegisterRequestDTO;
import com.fluidfunds.app.model.AccountTier;
import com.fluidfunds.app.model.UserModel;
import com.fluidfunds.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserModel registerUser(RegisterRequestDTO request) {
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
            throw new IllegalStateException("Email already in use.");
        });

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        UserModel newUser = UserModel.builder()
                .email(request.getEmail())
                .passwordHash(hashedPassword)
                .accountTier(AccountTier.STANDARD)
                .build();

        return userRepository.save(newUser);
    }

    public String loginUser(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserModel user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalStateException("User not found after authentication"));

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPasswordHash())
                .authorities("USER")
                .build();

        return jwtService.generateToken(userDetails);
    }
}