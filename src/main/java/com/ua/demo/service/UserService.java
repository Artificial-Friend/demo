package com.ua.demo.service;

import java.util.Optional;

import com.ua.demo.dto.UserDto;
import com.ua.demo.model.User;

public interface UserService {

    User create(User user);

    Optional<User> findByUsername(String username);
}
