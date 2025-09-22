package com.fluidfunds.app.service.user.impl;

import com.fluidfunds.app.exception.FluidFundsUserAlreadyCreatedException;
import com.fluidfunds.app.model.UserModel;
import com.fluidfunds.app.repository.UserRepository;
import com.fluidfunds.app.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserModel findUserByEmail(String username) {
        Optional<UserModel> user = userRepository.findByEmail(username);
        return user.orElse(null);
    }

    @Override
    public UserModel saveUser(UserModel newUser) {
        return userRepository.save(newUser);
    }
}
