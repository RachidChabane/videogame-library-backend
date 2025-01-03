package com.personalrchabane.videogame_library_backend.controller.game;

import com.personalrchabane.videogame_library_backend.dto.game.in.GameCreateDTO;
import com.personalrchabane.videogame_library_backend.dto.game.in.GameUpdateDTO;
import com.personalrchabane.videogame_library_backend.dto.game.out.GameOutDTO;
import com.personalrchabane.videogame_library_backend.service.game.GameService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {
    private final GameService gameService;
    private final int defaultPageSize;

    public GameController(GameService gameService, @Value("${game.pagesize}") int defaultPageSize) {
        this.gameService = gameService;
        this.defaultPageSize = defaultPageSize;
    }

    @GetMapping
    public ResponseEntity<Page<GameOutDTO>> getAllGames(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer releaseYear,
            @RequestParam(required = false) String studioName,
            @RequestParam(required = false) List<String> platforms,
            @RequestParam(defaultValue = "name,asc") String sort,
            @PageableDefault() Pageable pageable) {

        Pageable updatedPageable = Pageable.ofSize(defaultPageSize).withPage(pageable.getPageNumber());

        return ResponseEntity.ok(
                gameService.findFilteredAndSortedGames(name, genre, releaseYear, studioName, platforms, sort, updatedPageable)
        );
    }

    @GetMapping("/years")
    public ResponseEntity<List<Integer>> getAllUniqueReleaseYears() {
        return ResponseEntity.ok(gameService.findAllUniqueReleaseYears());
    }

    @GetMapping("/genres")
    public ResponseEntity<List<String>> getAllUniqueGenres() {
        return ResponseEntity.ok(gameService.findAllUniqueGenres());
    }

    @PostMapping
    public ResponseEntity<GameOutDTO> createGame(@RequestBody GameCreateDTO gameCreateDTO) {
        return ResponseEntity.ok(gameService.saveGame(gameCreateDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameOutDTO> getGameById(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.findGameById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameOutDTO> updateGame(
            @PathVariable Long id,
            @RequestBody GameUpdateDTO gameUpdateDTO) {
        return ResponseEntity.ok(gameService.updateGame(id, gameUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }
}
