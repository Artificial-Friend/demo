package com.ua.demo.configuration;

import com.ua.demo.model.User;
import com.ua.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User '" + username + "' not found"));
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder
                = org.springframework.security.core.userdetails.User.withUsername(username);
        userBuilder.password(user.getPassword());
        userBuilder.authorities(user.getRoles());
        return userBuilder.build();

    }
}
