package com.personalrchabane.videogame_library_backend.controller.game;

import com.personalrchabane.videogame_library_backend.dto.game.in.GameStudioCreateDTO;
import com.personalrchabane.videogame_library_backend.dto.game.out.GameStudioOutDTO;
import com.personalrchabane.videogame_library_backend.service.game.GameStudioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/studios")
public class GameStudioController {
    private final GameStudioService gameStudioService;

    public GameStudioController(GameStudioService gameStudioService) {
        this.gameStudioService = gameStudioService;
    }

    @GetMapping
    public List<GameStudioOutDTO> getAllStudios() {
        return gameStudioService.findAllStudios();
    }

    @PostMapping
    public ResponseEntity<GameStudioOutDTO> createStudio(@RequestBody GameStudioCreateDTO studioCreateDTO) {
        return ResponseEntity.ok(gameStudioService.saveStudio(studioCreateDTO));
    }
}
