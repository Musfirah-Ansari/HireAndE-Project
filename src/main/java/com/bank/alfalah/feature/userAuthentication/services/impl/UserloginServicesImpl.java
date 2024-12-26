package com.bank.alfalah.feature.userAuthentication.services.impl;

import com.bank.alfalah.feature.userAuthentication.dto.UserLoginRequest;
import com.bank.alfalah.feature.userAuthentication.dto.UserRegisterRequest;
import com.bank.alfalah.feature.userAuthentication.repository.UserLoginRepository;
import com.bank.alfalah.feature.userAuthentication.repository.entity.UserLogin;
import com.bank.alfalah.feature.userAuthentication.services.UserloginServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Service
public class UserloginServicesImpl implements UserloginServices {

    @Autowired
    UserLoginRepository userLoginRepository;

    @Autowired
    BCryptPasswordEncoder bcryptEncoder;

    @Override
    public UserLogin saveUser(UserRegisterRequest request) {
        log.trace("UserloginServicesImpl::User SaveOrUpdate failed with status [{}]",request.getUsername() );
        UserLogin user = new UserLogin();

        if(request.getSumsysuserid() ==0) {
            UserLogin findByUserId = userLoginRepository.findFirstByOrderBySumsysuseridDesc();
            user.setSumsysuserid(findByUserId.getSumsysuserid()+1);
        }else{
            user.setSumsysuserid(request.getSumsysuserid());
        }

        user.setUsername(request.getUsername());
       // user.setSum_first_name(request.getSum_first_name());
       // user.setSum_last_name(request.getSum_last_name());
        //user.setSum_full_name(request.getSum_full_name());
        user.setPassword(bcryptEncoder.encode(request.getPassword()));
       // user.setSum_axcs_token(request.getSum_axcs_token());
       // user.setUserGroup(user.getUserGroup(request.getSug_user_group_id()));
       // user.setSum_pwd_change_date(request.getSum_pwd_change_date());
        //user.setSum_pwd_expiry_days(request.getSum_pwd_expiry_days());
        //user.setSum_user_admin_auth_yn(request.getSum_user_admin_auth_yn());
        //user.setUserDesignation(request.getFsds_design_id());
        user.setEmail(request.getEmail());
        //user.setSum_user_signauth_yn(request.getSum_user_signauth_yn());
        //user.setSum_actvdir_user_yn(request.getSum_actvdir_user_yn());
        //user.setSum_lastlogin_date(request.getSum_lastlogin_date());
        //user.setSum_user_type(request.getSum_user_type());
        //user.setSum_user_category(request.getSum_user_category());
        //user.setSum_locked_status(request.getSum_locked_status());
        //user.setSum_activ_start_date(request.getSum_activ_start_date());
        ///user.setExpiryDate(request.getExpiryDate());
        //user.setSum_emp_code(request.getSum_emp_code());
        //user.setSum_enfrc_pwchg_yn(request.getSum_enfrc_pwchg_yn());
        //user.setSum_usr_wrng_pwd_cnt(request.getSum_usr_wrng_pwd_cnt());
        return userLoginRepository.save(user);
    }

    @Override
    public  UserLogin  saveToken(String token,  String username) {
        log.trace("UserloginServicesImpl::User Token SaveOrUpdate failed with status [{}]",username);
        UserLogin user_ = userLoginRepository.findByUsername(username);
        UserLogin userlogin = new UserLogin();
        userlogin.setSumsysuserid(user_.getSumsysuserid());
        userlogin.setUsername(user_.getUsername());
        //userlogin.setSum_first_name(user_.getSum_first_name());
        //userlogin.setSum_last_name(user_.getSum_last_name());
        //userlogin.setSum_full_name(user_.getSum_full_name());
        userlogin.setPassword(user_.getPassword());
        //userlogin.setSum_axcs_token(token);
       // userlogin.setSug_user_group_id(user_.getSug_user_group_id());
        //userlogin.setSum_pwd_change_date(user_.getSum_pwd_change_date());
        //userlogin.setSum_pwd_expiry_days(user_.getSum_pwd_expiry_days());
        //userlogin.setSum_user_admin_auth_yn(user_.getSum_user_admin_auth_yn());
       // userlogin.setFsds_design_id(user_.getFsds_design_id());
        //userlogin.setEmail(user_.getEmail());
        //userlogin.setSum_user_signauth_yn(user_.getSum_user_signauth_yn());
        //userlogin.setSum_actvdir_user_yn(user_.getSum_actvdir_user_yn());
        //userlogin.setSum_lastlogin_date(user_.getSum_lastlogin_date());
        //userlogin.setSum_user_type(user_.getSum_user_type());
        //userlogin.setSum_user_category(user_.getSum_user_category());
        //userlogin.setSum_locked_status(user_.getSum_locked_status());
        //userlogin.setSum_activ_start_date(user_.getSum_activ_start_date());
        //userlogin.setExpiryDate(user_.getExpiryDate());
        //userlogin.setSum_emp_code(user_.getSum_emp_code());
        //userlogin.setSum_enfrc_pwchg_yn(user_.getSum_enfrc_pwchg_yn());
        //userlogin.setSum_usr_wrng_pwd_cnt(0);
        return userLoginRepository.save(userlogin);
    }

    @Override
    public void userLogout(UserLoginRequest request, HttpServletRequest Session) {

        log.trace("UserloginServicesImpl::User logout and token de-Active  [{}]");
        UserLogin user_ = userLoginRepository.findByUsername(request.getUsername());
        UserLogin userlogin = new UserLogin();
        userlogin.setSumsysuserid(user_.getSumsysuserid());
        //userlogin.setSum_axcs_token("");
        userLoginRepository.save(userlogin);
    }
}


