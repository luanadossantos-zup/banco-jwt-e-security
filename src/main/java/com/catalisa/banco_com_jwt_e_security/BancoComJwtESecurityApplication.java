package com.catalisa.banco_com_jwt_e_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.catalisa.banco_com_jwt_e_security")
public class BancoComJwtESecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoComJwtESecurityApplication.class, args);
	}

}
