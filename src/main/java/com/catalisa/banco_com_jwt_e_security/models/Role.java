package com.catalisa.banco_com_jwt_e_security.models;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
