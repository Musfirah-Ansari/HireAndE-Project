package com.bank.alfalah.feature.userAuthentication.repository;


import com.bank.alfalah.feature.userAuthentication.repository.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {
    UserLogin findByUsername(String Sum_sys_user_code);
    UserLogin findFirstByOrderBySumsysuseridDesc();

    Optional<UserLogin> findByEmail(String Sum_sys_user_code);

}