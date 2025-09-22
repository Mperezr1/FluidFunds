package com.fluidfunds.app.service.auth.impl;

import com.fluidfunds.app.service.auth.FluidFundsPasswordEncoderService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultFluidFundsPasswordEncoderService implements FluidFundsPasswordEncoderService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
