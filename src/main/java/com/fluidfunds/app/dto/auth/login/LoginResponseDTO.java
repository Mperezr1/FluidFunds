package com.fluidfunds.app.dto.auth.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class LoginResponseDTO {
    private String accessToken;
}
