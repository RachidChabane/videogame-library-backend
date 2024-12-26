package com.personalrchabane.videogame_library_backend.controller.auth;

import com.personalrchabane.videogame_library_backend.dto.auth.AuthResponseDTO;
import com.personalrchabane.videogame_library_backend.dto.auth.LoginRequestDTO;
import com.personalrchabane.videogame_library_backend.dto.auth.RegisterRequestDTO;
import com.personalrchabane.videogame_library_backend.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        String token = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO registerRequest) {
        String token = authService.register(registerRequest.getUsername(),
                registerRequest.getEmail(),
                registerRequest.getPassword());
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}
