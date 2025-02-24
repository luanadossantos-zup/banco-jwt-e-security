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
import org.springframework.security.core.context.SecurityContextHolder;
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

        // Simule o departamento com base no usuário (você pode buscar isso do banco de dados)
         // Exemplo: pode ser "Finance" ou outro valor baseado no usuário

        // Define o contexto de segurança
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Recupera o usuário autenticado do banco de dados
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Obtém o departamento do usuário
        String department = user.getDepartment().getName();

        // Gera o token JWT com o departamento como claim
        String token = jwtTokenProvider.generateToken(authentication, department);

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
