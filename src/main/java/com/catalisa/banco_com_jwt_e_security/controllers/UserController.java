package com.catalisa.banco_com_jwt_e_security.controllers;

import com.catalisa.banco_com_jwt_e_security.dtos.UserResponse;
import com.catalisa.banco_com_jwt_e_security.infra.jwt.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {


    private final JwtTokenProvider jwtTokenProvider;

    public UserController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<?> adminRoute() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        // Verifica se o usuário tem o papel ROLE_USER
        if (authentication
                .getAuthorities()
                .stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER"))) {
            return ResponseEntity.status(403).body("Acesso negado para usuários com o papel ROLE_USER");
        }

        return ResponseEntity.ok(Map.of("message", "Acesso admin"));
    }


    @GetMapping("/user")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String authorizationHeader) {
        // Recupera o usuário autenticado
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("Usuário não autenticado");
        }
        // Remove o prefixo "Bearer " para obter apenas o token
        String token = authorizationHeader.substring(7);

        // Recupera o nome do usuário e o departamento do token
        String username = authentication.getName();
        String department = jwtTokenProvider.getDepartment(token);

        return ResponseEntity.ok(new UserResponse("Bem-vindo, " + username + "!" ,department));
    }


}
