package com.catalisa.banco_com_jwt_e_security.repositories;

import com.catalisa.banco_com_jwt_e_security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
