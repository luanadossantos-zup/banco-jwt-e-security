package com.catalisa.banco_com_jwt_e_security;

import com.catalisa.banco_com_jwt_e_security.models.Department;
import com.catalisa.banco_com_jwt_e_security.models.Role;
import com.catalisa.banco_com_jwt_e_security.models.User;
import com.catalisa.banco_com_jwt_e_security.repositories.DepartmentRepository;
import com.catalisa.banco_com_jwt_e_security.repositories.RoleRepository;
import com.catalisa.banco_com_jwt_e_security.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RoleRepository roleRepository; // Certifique-se de ter um repositório para Role

    @BeforeEach
    void setUp() {
        // Crie um departamento para associar ao usuário
        Department department = new Department();
        department.setName("IT");
        department = departmentRepository.save(department);

        // Crie um role para associar ao usuário
        Role role = new Role();
        role.setName("ROLE_USER");
        role = roleRepository.save(role);

        // Crie um usuário e associe o departamento e o role
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setDepartment(department);
        user.setRoles(Set.of(role)); // Adiciona o role ao usuário

        // Salve o usuário no banco de dados
        userRepository.save(user);
    }

    @Test
    void testFindByUsername() {
        Optional<User> user = userRepository.findByUsername("testuser");
        assertTrue(user.isPresent());
        System.out.println("Usuário encontrado: " + user.get().getUsername());
    }
}
