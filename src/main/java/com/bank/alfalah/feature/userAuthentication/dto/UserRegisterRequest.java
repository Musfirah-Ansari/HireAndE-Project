package com.bank.alfalah.feature.userAuthentication.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserRegisterRequest {

    int sumsysuserid;

    String username;

    String sum_first_name;

    String sum_last_name;

    String sum_full_name;

    String password;

    String sum_axcs_token;

    int  sug_user_group_id;

    int  fsds_design_id;

    Date sum_pwd_change_date;

    int sum_pwd_expiry_days;

    String sum_user_admin_auth_yn;

    String email;

    String sum_user_signauth_yn;

    String sum_actvdir_user_yn;

    Date sum_lastlogin_date;

    String sum_user_type;

    String sum_user_category;

    String sum_status;

    String sum_locked_status;

    int sum_cruser;

    String sum_emp_code;

    Date sum_token_validate;

    LocalDate sum_activ_start_date;

    LocalDate expiryDate;

    String sum_enfrc_pwchg_yn;

    int sum_usr_wrng_pwd_cnt;


}
