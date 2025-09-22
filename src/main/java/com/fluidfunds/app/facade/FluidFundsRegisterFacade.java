package com.fluidfunds.app.facade;

import com.fluidfunds.app.dto.register.RegisterRequestDTO;
import com.fluidfunds.app.dto.register.RegisterResponseDTO;
import com.fluidfunds.app.model.UserModel;

public interface FluidFundsRegisterFacade {

    RegisterResponseDTO registerUser(final RegisterRequestDTO registerRequestDTO);

}
