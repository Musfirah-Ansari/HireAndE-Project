package com.bank.alfalah.feature.userAuthentication.repository.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "app_user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLogin  implements Serializable {

    @Id
    @Column(name = "Userid")
    long sumsysuserid;

    @Column(name = "password")
    String password;

    @Column(name = "state")
    String state;

    @Column(name = "EMPID")
    int EMPID;

    @Column(name = "username")
    String username;

    @Column(name = "email")
    String email;

}

