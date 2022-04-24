package com.ua.demo.controller;

import javax.validation.Valid;

import com.ua.demo.dto.UserDto;
import com.ua.demo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MainController implements ErrorController {
    public static final String REGISTRATION_PAGE = "register";

    private final AuthService authService;

    @GetMapping({"", "/"})
    public String loginPage() {
        return "index";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/" + REGISTRATION_PAGE)
    public String register(final Model model) {
        model.addAttribute("userDto", new UserDto());
        return REGISTRATION_PAGE;
    }

    @PostMapping("/" + REGISTRATION_PAGE)
    public String userRegistration(final @Valid UserDto userDto, final BindingResult bindingResult) {
        if (!userDto.getPassword().equals(userDto.getMatchingPassword())) {
            bindingResult.addError(new FieldError("userDto", "error", "Passwords doesn't match"));
            return REGISTRATION_PAGE;
        }
        try {
            authService.register(userDto.getUsername(), userDto.getPassword());
        } catch (Exception e) {
            bindingResult.addError(new FieldError("userDto", "username", "This username already exists"));
            return REGISTRATION_PAGE;
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
