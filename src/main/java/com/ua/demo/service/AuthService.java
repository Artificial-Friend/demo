package com.ua.demo.service;

import com.ua.demo.model.User;

public interface AuthService {

    User register(String email, String password) throws Exception;
}
