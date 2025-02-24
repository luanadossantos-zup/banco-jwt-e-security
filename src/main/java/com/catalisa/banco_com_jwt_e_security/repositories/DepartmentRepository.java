package com.catalisa.banco_com_jwt_e_security.repositories;

import com.catalisa.banco_com_jwt_e_security.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<DepartmentRepository, Long> {
    Optional<Department> findByName(String name);
}
