package com.bank.alfalah.feature.userAuthentication.facade;

import com.bank.alfalah.feature.userAuthentication.dto.UserLoginRequest;
import com.bank.alfalah.feature.userAuthentication.dto.UserRegisterRequest;
import com.bank.alfalah.feature.userAuthentication.dto.UserloginResponse;
import com.bank.alfalah.feature.userAuthentication.repository.entity.UserLogin;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface UserLoginFacade {

    UserloginResponse getUserAuthentication(UserLoginRequest authenticationRequest) ;
    UserLogin CreateUser(UserRegisterRequest request);
   //ResponseEntity<?> refreshToken(HttpServletRequest request) throws Exception;

    UserLogin worngLoginAttempt(String Username);

    void userLogout(UserLoginRequest request, HttpServletRequest Session) ;

}
