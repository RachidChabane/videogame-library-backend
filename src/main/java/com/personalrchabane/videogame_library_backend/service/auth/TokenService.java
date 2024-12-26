package com.personalrchabane.videogame_library_backend.service.auth;

public interface TokenService {
    String generateToken(String username);
    boolean validateToken(String token);
}
