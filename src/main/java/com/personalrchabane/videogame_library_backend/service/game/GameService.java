package com.personalrchabane.videogame_library_backend.service.game;

import com.personalrchabane.videogame_library_backend.dto.game.in.GameCreateDTO;
import com.personalrchabane.videogame_library_backend.dto.game.out.GameOutDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GameService {
    Page<GameOutDTO> findFilteredAndSortedGames(String name, String genre, Integer releaseYear, String studioName, List<String> platforms, String sort, Pageable pageable);
    GameOutDTO findGameById(Long id);
    GameOutDTO saveGame(GameCreateDTO gameCreateDTO);
    GameOutDTO updateGame(Long id, GameCreateDTO gameCreateDTO);
    void deleteGame(Long id);

    List<Integer> findAllUniqueReleaseYears();

    List<String> findAllUniqueGenres();
}