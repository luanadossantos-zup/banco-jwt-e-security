package com.catalisa.banco_com_jwt_e_security.controllers;


import com.catalisa.banco_com_jwt_e_security.dtos.JwtResponse;
import com.catalisa.banco_com_jwt_e_security.dtos.LoginDto;
import com.catalisa.banco_com_jwt_e_security.infra.jwt.JwtTokenProvider;
import com.catalisa.banco_com_jwt_e_security.models.User;
import com.catalisa.banco_com_jwt_e_security.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );


        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        String department = user.getDepartment().getName();


        String token = jwtTokenProvider.generateToken(authentication, department);

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
