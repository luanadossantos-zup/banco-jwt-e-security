package com.catalisa.banco_com_jwt_e_security.dtos;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
