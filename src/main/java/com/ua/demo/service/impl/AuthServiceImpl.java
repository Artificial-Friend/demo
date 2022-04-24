package com.ua.demo.service.impl;

import java.util.Optional;
import java.util.Set;

import com.ua.demo.model.User;
import com.ua.demo.service.AuthService;
import com.ua.demo.service.RoleService;
import com.ua.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final RoleService roleService;

    @Override
    public User register(final String username, final String password) throws Exception {
        final Optional<User> byUsername = userService.findByUsername(username);
        if (byUsername.isEmpty()) {
            final User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRoles(Set.of(roleService.getRoleByName("USER").orElseThrow(() -> new Exception("Role USER does not exist"))));
            userService.create(user);
            return user;
        }
        throw new Exception("Current user already exist: " + username);
    }
}
