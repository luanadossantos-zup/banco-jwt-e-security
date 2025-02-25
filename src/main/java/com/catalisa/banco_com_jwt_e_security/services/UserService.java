package com.catalisa.banco_com_jwt_e_security.services;

import com.catalisa.banco_com_jwt_e_security.dtos.RegisterUserDto;

public interface UserService {

    public void registerUser(RegisterUserDto registerUserDto);
}
