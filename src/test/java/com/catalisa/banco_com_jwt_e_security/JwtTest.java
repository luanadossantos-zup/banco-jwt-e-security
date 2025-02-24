package com.catalisa.banco_com_jwt_e_security;

public class JwtTest {
    public static void main(String[] args) {
        try {
            Class.forName("io.jsonwebtoken.impl.DefaultJwtBuilder");
            System.out.println("Classe carregada com sucesso!");
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar a classe: " + e.getMessage());
        }
    }
}
