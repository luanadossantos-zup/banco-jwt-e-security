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

        Department department = new Department();
        department.setName("IT");
        department = departmentRepository.save(department);


        Role role = new Role();
        role.setName("ROLE_USER");
        role = roleRepository.save(role);

        // Cria um usuário
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setDepartment(department);
        user.setRoles(Set.of(role));

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
