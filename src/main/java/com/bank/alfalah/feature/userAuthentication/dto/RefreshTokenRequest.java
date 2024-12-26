package com.bank.alfalah.feature.userAuthentication.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RefreshTokenRequest {

    @NotEmpty
    String token;
}