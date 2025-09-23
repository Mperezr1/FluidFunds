package com.fluidfunds.app.facade;

import com.fluidfunds.app.dto.auth.register.RegisterRequestDTO;
import com.fluidfunds.app.dto.auth.register.RegisterResponseDTO;

public interface FluidFundsRegisterFacade {

    RegisterResponseDTO registerUser(final RegisterRequestDTO registerRequestDTO);

}
