package com.fluidfunds.app.dto.auth.login;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
}
