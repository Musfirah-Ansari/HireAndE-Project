package com.bank.alfalah.feature.userAuthentication.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserLoginRequest {

     Long sum_sys_user_id;
     String username;
     String password;
     String email;

}
