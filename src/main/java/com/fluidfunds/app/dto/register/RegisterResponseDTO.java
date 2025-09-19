package com.fluidfunds.app.dto.register;

import com.fluidfunds.app.model.AccountTier;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class RegisterResponseDTO {
    private UUID id;
    private String email;
    private AccountTier accountTier;
    private LocalDateTime createdAt;
}