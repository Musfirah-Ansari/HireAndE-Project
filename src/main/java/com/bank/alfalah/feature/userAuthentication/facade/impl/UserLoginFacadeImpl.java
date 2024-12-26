package com.bank.alfalah.feature.userAuthentication.facade.impl;


import com.bank.alfalah.exception.ServiceException;
import com.bank.alfalah.exception.constant.ErrorCodeEnum;
import com.bank.alfalah.exception.dto.RecordNotFoundException;
import com.bank.alfalah.feature.userAuthentication.dto.UserLoginRequest;
import com.bank.alfalah.feature.userAuthentication.dto.UserRegisterRequest;
import com.bank.alfalah.feature.userAuthentication.dto.UserloginResponse;
import com.bank.alfalah.feature.userAuthentication.facade.UserLoginFacade;
import com.bank.alfalah.feature.userAuthentication.repository.UserLoginRepository;
import com.bank.alfalah.feature.userAuthentication.repository.entity.UserLogin;
import com.bank.alfalah.feature.userAuthentication.services.UserloginServices;
import com.bank.alfalah.security.JwtTokenUtil;
import com.bank.alfalah.security.JwtUserDetailsService;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.bank.alfalah.exception.constant.RoleConstant.INVALID_CREDENTIALS;

@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserLoginFacadeImpl implements UserLoginFacade {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    JwtUserDetailsService userDetailsService;

    @Autowired
    UserloginServices userloginServices;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserLoginRepository userLoginRepository;


    ModelMapper modelMapper;

    /*
    @Autowired
    TokenEndpoint tokenEndpoint;

    JwtConfigProperties jwtConfigProperties;
     */

    public UserloginResponse getUserAuthentication(UserLoginRequest request)  {

        log.trace("get User Authentication [{}]", request);

        try{
            authenticate(request.getUsername(), request.getPassword());
        }catch (Exception ex){
            throw new RecordNotFoundException("Authenticate Exception....!: " + ex.getMessage().toString());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        boolean isPasswordMatches = passwordEncoder.matches(request.getPassword(),userDetails.getPassword());

        if(isPasswordMatches != true){
            throw new RecordNotFoundException("You have entered an Invalid credentials. Please try again!: " + request.getPassword());
        }
        final String token = jwtTokenUtil.generateToken(userDetails);

        userloginServices.saveToken(token, request.getUsername());

        log.trace("Generate Token [{}]", token);
        return new UserloginResponse(token);
    }

    @Override
    public UserLogin CreateUser(UserRegisterRequest request){
        log.trace("Creating register User [{}]", request);
        return userloginServices.saveUser(request);
    }


    private UserLogin build(final UserRegisterRequest request) {
        UserLogin userLogin = new UserLogin();
        modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
        TypeMap<UserRegisterRequest, UserLogin> typeMap = modelMapper.typeMap(UserRegisterRequest.class, UserLogin.class);
        typeMap.addMappings(mapper -> mapper.skip(UserLogin::setSumsysuserid));
        typeMap.map(request, userLogin);
        return userLogin;
    }

    public UserLogin worngLoginAttempt(String Username) {
        log.trace("UserloginImpl::User wrong attempt Save Or Update [{}]");

        UserLogin userlogin = new UserLogin();
        UserLogin user = userLoginRepository.findByUsername(Username);
 /*       if(user.getSum_usr_wrng_pwd_cnt() < 3){
            userlogin.setSumsysuserid(user.getSumsysuserid());
            userlogin.setUsername(user.getUsername());
            userlogin.setPassword(user.getPassword());
//            userlogin.setSum_axcs_token(user.getSum_axcs_token());
//            userlogin.setSum_pwd_expiry_days(user.getSum_pwd_expiry_days());
//            userlogin.setSum_user_admin_auth_yn(user.getSum_user_admin_auth_yn());
            userlogin.setEmail(user.getEmail());
//            userlogin.setSum_locked_status(user.getSum_locked_status());
//            userlogin.setExpiryDate(user.getExpiryDate());
//            userlogin.setSum_usr_wrng_pwd_cnt(user.getSum_usr_wrng_pwd_cnt()+1);
            return userLoginRepository.save(userlogin);
        }else{
            throw new RecordNotFoundException("You have entered an Invalid credentials 3 times User has been Locked!:");
        }*/
    return  null;
    }

    @Override
    public void userLogout(UserLoginRequest request, HttpServletRequest Session) {
        userloginServices.userLogout(request,  Session);
    }

    public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
    }

    private void authenticate(String username, String password) throws Exception {
        log.trace("Checking User Authentication Manager [{}]", username,password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new ServiceException(ErrorCodeEnum.UN_AUTHORIZED, INVALID_CREDENTIALS);
        } catch (BadCredentialsException e) {
            worngLoginAttempt(username);
                throw new RecordNotFoundException("You have entered an Invalid credentials. Please try again!:");
        }
    }
}
