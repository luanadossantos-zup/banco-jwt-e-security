package com.catalisa.banco_com_jwt_e_security.services;

import com.catalisa.banco_com_jwt_e_security.dtos.LoginDto;

public interface AuthService {

    String login(LoginDto loginDto);
}
