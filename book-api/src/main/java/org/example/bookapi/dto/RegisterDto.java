package org.example.bookapi.dto;

public record RegisterDto (

        String name,
        String username,
        String password,
        String email

){}