package com.ua.demo.service.impl;

import java.util.Optional;

import com.ua.demo.model.Role;
import com.ua.demo.repository.RoleRepository;
import com.ua.demo.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    @Override
    public Role create(final Role role) {
        return repository.save(role);
    }

    @Override
    public Optional<Role> getRoleByName(final String roleName) {
        return repository.findRoleByName(roleName);
    }
}
