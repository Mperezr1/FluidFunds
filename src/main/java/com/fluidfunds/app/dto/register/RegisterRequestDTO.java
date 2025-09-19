package com.fluidfunds.app.dto.register;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String email;
    private String password;
}