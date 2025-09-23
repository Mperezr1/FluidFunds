package com.fluidfunds.app.facade;

import com.fluidfunds.app.dto.auth.login.LoginRequestDTO;
import org.springframework.http.ResponseCookie;

public interface FluidFundsLoginFacade {

    ResponseCookie loginUserAngGetJWT(LoginRequestDTO loginRequestDTO);

}
