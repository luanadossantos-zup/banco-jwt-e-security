package com.catalisa.banco_com_jwt_e_security.services;

import com.catalisa.banco_com_jwt_e_security.dtos.LoginDto;
import com.catalisa.banco_com_jwt_e_security.infra.jwt.JwtTokenProvider;
import com.catalisa.banco_com_jwt_e_security.models.User;
import com.catalisa.banco_com_jwt_e_security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository userRepository;

    public String login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);


            // Buscar o usuário autenticado
            User user = userRepository.findByUsername(loginDto.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


            String department = user.getDepartment().getName();

            String token = jwtTokenProvider.generateToken(authentication,department);

            return token;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao autenticar o usuário");
        }
    }
}
