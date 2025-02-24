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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
