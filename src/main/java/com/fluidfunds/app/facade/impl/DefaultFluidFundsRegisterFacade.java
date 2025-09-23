package com.fluidfunds.app.facade.impl;

import com.fluidfunds.app.dto.auth.register.RegisterRequestDTO;
import com.fluidfunds.app.dto.auth.register.RegisterResponseDTO;
import com.fluidfunds.app.exception.FluidFundsUserAlreadyCreatedException;
import com.fluidfunds.app.facade.FluidFundsRegisterFacade;
import com.fluidfunds.app.model.AccountTier;
import com.fluidfunds.app.model.UserModel;
import com.fluidfunds.app.service.auth.FluidFundsPasswordEncoderService;
import com.fluidfunds.app.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
@AllArgsConstructor
public class DefaultFluidFundsRegisterFacade implements FluidFundsRegisterFacade {

    private UserService userService;
    private FluidFundsPasswordEncoderService passwordEncoderService;

    @Override
    public RegisterResponseDTO registerUser(RegisterRequestDTO registerRequestDTO) {
        if (ObjectUtils.isEmpty(userService.findUserByEmail(registerRequestDTO.getEmail()))) {
            String hashedPassword = passwordEncoderService.encodePassword(registerRequestDTO.getPassword());
            UserModel newUser = saveUser(registerRequestDTO.getEmail(), hashedPassword);
            return getRegisterResponseDTO(newUser);
        } else {
            throw new FluidFundsUserAlreadyCreatedException(registerRequestDTO.getEmail());
        }
    }

    private static RegisterResponseDTO getRegisterResponseDTO(UserModel newUser) {
        return RegisterResponseDTO.builder()
                .id(newUser.getId())
                .email(newUser.getEmail())
                .accountTier(newUser.getAccountTier())
                .createdAt(newUser.getCreatedAt())
                .build();
    }

    private UserModel saveUser(final String username, final String hashedpassword){
        UserModel newUser = UserModel.builder()
                .email(username)
                .passwordHash(hashedpassword)
                .accountTier(AccountTier.STANDARD)
                .build();

        return userService.saveUser(newUser);
    }
}
