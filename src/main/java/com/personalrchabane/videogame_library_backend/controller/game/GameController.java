package com.personalrchabane.videogame_library_backend.controller.game;

import com.personalrchabane.videogame_library_backend.dto.game.in.GameCreateDTO;
import com.personalrchabane.videogame_library_backend.dto.game.out.GameOutDTO;
import com.personalrchabane.videogame_library_backend.service.game.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<GameOutDTO>> getAllGames(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer releaseYear,
            @RequestParam(required = false) String studioName,
            @RequestParam(required = false) List<String> platforms,
            @RequestParam(defaultValue = "id,asc") String sort) {
        return ResponseEntity.ok(gameService.findFilteredAndSortedGames(name, genre, releaseYear, studioName, platforms, sort));
    }

    @PostMapping
    public ResponseEntity<GameOutDTO> createGame(@RequestBody GameCreateDTO gameCreateDTO) {
        return ResponseEntity.ok(gameService.saveGame(gameCreateDTO));
    }
}
