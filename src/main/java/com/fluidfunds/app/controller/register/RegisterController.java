package com.fluidfunds.app.controller.register;

import com.fluidfunds.app.dto.register.RegisterRequestDTO;
import com.fluidfunds.app.dto.register.RegisterResponseDTO;
import com.fluidfunds.app.facade.FluidFundsRegisterFacade;
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
public class RegisterController {

    private final FluidFundsRegisterFacade registerFacade;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        return new ResponseEntity<>(registerFacade.registerUser(request), HttpStatus.CREATED);
    }

}
