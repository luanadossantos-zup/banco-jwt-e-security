package com.catalisa.banco_com_jwt_e_security.dtos;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;

    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }


}
