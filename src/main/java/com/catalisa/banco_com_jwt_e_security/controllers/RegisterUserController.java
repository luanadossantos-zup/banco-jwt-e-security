package com.catalisa.banco_com_jwt_e_security.controllers;

import com.catalisa.banco_com_jwt_e_security.dtos.RegisterUserDto;
import com.catalisa.banco_com_jwt_e_security.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class RegisterUserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @PostMapping
    public void registerUser(@RequestBody RegisterUserDto registerUserDto){
        userServiceImp.registerUser(registerUserDto);
    }
}
