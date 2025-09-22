package com.fluidfunds.app.facade;

import com.fluidfunds.app.dto.login.LoginRequestDTO;
import com.fluidfunds.app.dto.login.LoginResponseDTO;

public interface FluidFundsLoginFacade {

    LoginResponseDTO loginUserAngGetJWT(LoginRequestDTO loginRequestDTO);

}
