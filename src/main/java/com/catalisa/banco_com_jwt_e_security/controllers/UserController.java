package com.catalisa.banco_com_jwt_e_security.controllers;

import com.catalisa.banco_com_jwt_e_security.dtos.UserResponse;
import com.catalisa.banco_com_jwt_e_security.infra.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {


    private final JwtTokenProvider jwtTokenProvider;

    public UserController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping
    public Map<String, Date> returnDate(){
        return Map.of("dateNow", new Date());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public Map<String, String> adminRoute(){
        return Map.of("message", "Acesso admin");
    }


    @GetMapping("/user")
    public ResponseEntity<?> getUserInfo(HttpServletRequest request) {
        // Extrai o token do cabeçalho Authorization
        String token = request.getHeader("Authorization").replace("Bearer ", "");

        // Valida o token
        if (!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(401).body("Token inválido ou expirado");
        }

        // Recupera o nome do usuário e o departamento do token
        String username = jwtTokenProvider.getUsername(token);
        String department = jwtTokenProvider.getDepartment(token);

        // Retorna a resposta com as informações do usuário
        return ResponseEntity.ok(new UserResponse("Bem-vindo, " + username + "!", department));
    }


}
