package com.ua.demo.service;

import java.util.Optional;

import com.ua.demo.model.Role;

public interface RoleService {

    Role create(Role role);
    Optional<Role> getRoleByName(String roleName);
}
