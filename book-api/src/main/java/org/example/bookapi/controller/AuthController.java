package org.example.bookapi.controller;


import org.example.bookapi.dto.LoginDto;
import org.example.bookapi.dto.RegisterDto;
import org.example.bookapi.entity.Customer;
import org.example.bookapi.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register (@RequestBody RegisterDto registerDto) {
        String message = authService.register(registerDto);
        return message;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        String message = authService.login(loginDto);
        return message;
    }

    @GetMapping("/all")
    public List<Customer> getCustomers() {
        return authService.getAllCustomers();
    }
}
