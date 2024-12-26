package com.personalrchabane.videogame_library_backend.dto.auth;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String username;
    private String email;
    private String password;
}
