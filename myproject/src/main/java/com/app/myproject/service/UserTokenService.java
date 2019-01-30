package com.app.myproject.service;

import com.app.myproject.model.User;
import com.app.myproject.model.UserToken;

public interface UserTokenService {

    String createUserToken(User user);

    UserToken getUserToken(String token);

}
