package com.ua.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDto {

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 4, max = 20)
    private String password;

    @NotBlank
    private String matchingPassword;
}
