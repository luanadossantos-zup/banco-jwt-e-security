package com.catalisa.banco_com_jwt_e_security.services;

import com.catalisa.banco_com_jwt_e_security.dtos.RegisterUserDto;
import com.catalisa.banco_com_jwt_e_security.models.Department;
import com.catalisa.banco_com_jwt_e_security.models.Role;
import com.catalisa.banco_com_jwt_e_security.models.User;
import com.catalisa.banco_com_jwt_e_security.repositories.DepartmentRepository;
import com.catalisa.banco_com_jwt_e_security.repositories.RoleRepository;
import com.catalisa.banco_com_jwt_e_security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void registerUser(RegisterUserDto registerUserDto){
        if (userRepository.existsByUsername(registerUserDto.getUsername())){
            throw new RuntimeException("Unprocess Entity");
        }

        User user = new User();
        user.setUsername(registerUserDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registerUserDto.getPassword()));

        Set<Role> roles = registerUserDto
                .getRoles()
                .stream()
                .map(r -> new Role(r.name())).collect(Collectors.toSet());
        roleRepository.saveAll(roles);

        user.setRoles(roles);

        Department department = departmentRepository.findByName(registerUserDto.getDepartment())
                .orElseGet(() -> {
                    Department newDepartment = new Department(registerUserDto.getDepartment());
                    return departmentRepository.save(newDepartment);
                });
        user.setDepartment(department);

        userRepository.save(user);

    }
}
