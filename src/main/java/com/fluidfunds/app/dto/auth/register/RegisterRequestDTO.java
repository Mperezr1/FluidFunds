package com.fluidfunds.app.dto.auth.register;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String email;
    private String password;
}