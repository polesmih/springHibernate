package com.example.hibernate.service;

import com.example.hibernate.domain.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findRoleByName(String name);
}
