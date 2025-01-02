package com.personalrchabane.videogame_library_backend.service.auth;

import com.personalrchabane.videogame_library_backend.model.user.enums.Role;
import io.jsonwebtoken.Claims;

public interface TokenService {
    String generateToken(String username, String email, Role role);
    String extractUsername(String token);
    boolean validateToken(String token);
    Claims extractAllClaims(String token);
}
