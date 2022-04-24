package com.ua.demo.configuration;

import javax.annotation.PostConstruct;

import java.util.HashSet;
import java.util.Set;

import com.ua.demo.model.Role;
import com.ua.demo.model.User;
import com.ua.demo.service.RoleService;
import com.ua.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InitTest {
    private final RoleService roleService;
    private final UserService userService;

    @PostConstruct
    public void inject() {
        final Role adminRole = new Role();
        adminRole.setName("ADMIN");
        roleService.create(adminRole);
        final Role userRole = new Role();
        userRole.setName("USER");
        roleService.create(userRole);
        final Set<Role> roles = new HashSet<>();

        final User userUser = new User();
        userUser.setPassword("user");
        userUser.setUsername("user");
        roles.add(userRole);
        userUser.setRoles(roles);
        userService.create(userUser);

        final User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword("admin");
        roles.add(adminRole);
        adminUser.setRoles(roles);
        userService.create(adminUser);
    }
}
