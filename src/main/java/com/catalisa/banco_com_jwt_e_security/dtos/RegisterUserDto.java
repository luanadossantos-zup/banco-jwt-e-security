package com.catalisa.banco_com_jwt_e_security.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterUserDto {

    private String username;
    private String password;
    private Set<Roles> roles;
    private String department;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
