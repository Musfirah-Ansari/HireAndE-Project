package com.bank.alfalah.feature.userAuthentication.services;

import com.bank.alfalah.feature.userAuthentication.dto.UserLoginRequest;
import com.bank.alfalah.feature.userAuthentication.dto.UserRegisterRequest;
import com.bank.alfalah.feature.userAuthentication.repository.entity.UserLogin;

import javax.servlet.http.HttpServletRequest;

public interface UserloginServices {

    UserLogin saveUser(UserRegisterRequest request);

    UserLogin  saveToken(String token, String Username);

    void userLogout(UserLoginRequest request, HttpServletRequest Session) ;

}
