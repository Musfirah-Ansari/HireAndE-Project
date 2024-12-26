package com.bank.alfalah.feature.userAuthentication.repository.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "HireAtE_SysUser")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SystemUserMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SYS_USER_ID")
    int sysUserId;

    @Column(name = "SYS_USER_CODE", nullable = false, length = 50)
    String sysUserCode;

    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 50)
    String lastName;

    @Column(name = "FULL_NAME", nullable = false, length = 50)
    String fullName;

    @Column(name = "USER_PASSWORD", nullable = false, length = 50)
    String userPassword;

    @Column(name = "AXCS_TOKEN", length = 50)
    String axcsToken;

    @Column(name = "PWD_COMPLEXITY", length = 50)
    String pwdComplexity;

    @Column(name = "PWD_CHANGE_DATE")
    LocalDate pwdChangeDate;

    @Column(name = "PWD_EXPIRY_DAYS")
    Integer pwdExpiryDays;

    @Column(name = "USER_ADMIN_AUTH_YN", length = 1)
    String userAdminAuthYn;

    @Column(name = "USER_EMAIL_ADDR", length = 50)
    String userEmailAddr;

    @Column(name = "ACTVDIR_USER_YN", length = 50)
    String actvdirUserYn;

    @Column(name = "LASTLOGIN_DATE")
    LocalDate lastLoginDate;

    @Column(name = "USER_TYPE", length = 50)
    String userType;

    @Column(name = "STATUS", length = 1)
    String status;

    @Column(name = "LOCKED_STATUS", length = 1)
    String lockedStatus;

    @Column(name = "ACTIV_START_DATE")
    LocalDate activStartDate;

    @Column(name = "ACTIV_END_DATE")
    LocalDate activEndDate;

    @Column(name = "CRUSER", length = 50)
    String crUser;

    @Column(name = "CRDATE")
    LocalDate crDate;

    @Column(name = "CHKUSER", length = 50)
    String chkUser;

    @Column(name = "CHKDATE", length = 50)
    String chkDate;

    @Column(name = "USER_IPADDR", length = 50)
    String userIpAddr;

    @Column(name = "EMP_CODE", length = 50)
    String empCode;

    @Column(name = "ENFRC_PWCHG_YN", length = 50)
    String enfRcPwChgYn;

    @Column(name = "USR_WRNG_PWD_CNT")
    Integer usrWrngPwdCnt;

    @Column(name = "WRNG_PWD_DATE")
    LocalDate wrngPwdDate;

    @Column(name = "TOKEN_VALIDATE", length = 1)
    String tokenValidate;
}
