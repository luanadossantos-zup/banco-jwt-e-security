package com.catalisa.banco_com_jwt_e_security.dtos;

public class UserResponse {

    private String message;
    private String department;

    public UserResponse(String message, String department) {
        this.message = message;
        this.department = department;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
