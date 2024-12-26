package com.personalrchabane.videogame_library_backend.service.auth;

public interface AuthService {
    String login(String username, String password);
    String register(String username, String email, String password);
}
