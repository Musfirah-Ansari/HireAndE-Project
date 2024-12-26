package com.bank.alfalah.security;


import com.bank.alfalah.exception.dto.RecordNotFoundException;
import com.bank.alfalah.feature.userAuthentication.repository.UserLoginRepository;
import com.bank.alfalah.feature.userAuthentication.repository.entity.UserLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserLoginRepository userLoginRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws RecordNotFoundException {

        UserLogin user = userLoginRepository.findByUsername(username);

        if (user == null) {
            throw new RecordNotFoundException("You have entered an Invalid credentials. Please try again!: ");
        }

        if(user.getSum_locked_status().contains("Y")){
            throw new RecordNotFoundException("User account Locked, Please contact System Administrator!:");
        }
        if(user.getExpiryDate()!=null) {

           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-YYYY");
           LocalDateTime now = LocalDateTime.now();
           LocalDate sysDate = LocalDate.of(now.getYear(),now.getMonth(),now.getDayOfMonth());

           SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
            if(sysDate.isEqual(user.getExpiryDate())){
                throw new RecordNotFoundException("Your Password has been expired, Please connect System Administrator!:");
            }
        }
        if(user.getSum_usr_wrng_pwd_cnt() == 3){
            throw new RecordNotFoundException("You have entered an Invalid credentials 3 times User has been Locked!:");
        }
        List<GrantedAuthority> authorities = getUserAuthority(user.getUsername());
        return buildUserForAuthentication(user, authorities);
    }

/*
    private List<GrantedAuthority> getUserAuthority(Set<UserGroup> userGroups_) {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (UserGroup userGroup : userGroups_) {
            roles.add(new SimpleGrantedAuthority(userGroup.getSug_user_group_descr()));
        }
        return new ArrayList<>(roles);
    }
*/

    private List<GrantedAuthority> getUserAuthority(String AdminRole) {
        //System.out.println(""+AdminRole);
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(AdminRole));
       // System.out.println(""+roles.size());
        return new ArrayList<>(roles);
    }
    private UserDetails buildUserForAuthentication(UserLogin user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                true, true, true, true, authorities);
    }
}
