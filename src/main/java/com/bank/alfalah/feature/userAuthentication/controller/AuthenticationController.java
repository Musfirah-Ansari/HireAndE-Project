package com.bank.alfalah.feature.userAuthentication.controller;


import com.bank.alfalah.feature.userAuthentication.dto.UserLoginRequest;
import com.bank.alfalah.feature.userAuthentication.dto.UserRegisterRequest;
import com.bank.alfalah.feature.userAuthentication.dto.UserloginResponse;
import com.bank.alfalah.feature.userAuthentication.facade.UserLoginFacade;
import com.bank.alfalah.feature.userAuthentication.repository.entity.UserLogin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api(tags = "api/auth_controller/")
@RequestMapping("/api/auth_controller/")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthenticationController {

    @Autowired
    UserLoginFacade userLoginFacade;


    @PostMapping(value = "/authenticate" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get user login token", nickname = "createAuthenticationToken", notes = "create Authentication Token")
    public ResponseEntity<UserloginResponse> createAuthenticationToken(@Valid @RequestBody UserLoginRequest authenticationRequest)  {

        return ResponseEntity.ok(userLoginFacade.getUserAuthentication(authenticationRequest));
    }

    @PostMapping("afnan")
    public String abc(){
        return "AFnan";
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Register new user", nickname = "createUser", notes = "Register New User")
    public ResponseEntity<UserLogin> registerUser(@Valid @RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(userLoginFacade.CreateUser(request));
    }

    /*@PostMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Logout employee", nickname = "logOut", notes = "Invalidate the user token")
    public void logoutEmployee(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) {
        userLoginFacade.userLogout(null,null);
    }*/

    @GetMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Logout employee", nickname = "logOut", notes = "Invalidate the user token")
    public ResponseEntity<String> logoutEmployee(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) {
        // Logic to handle logout can be removed or retained as needed
        return ResponseEntity.ok("Hello");
    }


}
