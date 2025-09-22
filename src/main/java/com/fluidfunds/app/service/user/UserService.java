package com.fluidfunds.app.service.user;

import com.fluidfunds.app.model.UserModel;

public interface UserService {

    UserModel findUserByEmail(final String username);

    UserModel saveUser(final UserModel newUser);
}
