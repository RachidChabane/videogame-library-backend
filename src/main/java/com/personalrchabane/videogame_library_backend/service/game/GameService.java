package com.personalrchabane.videogame_library_backend.service.game;

import com.personalrchabane.videogame_library_backend.dto.game.in.GameCreateDTO;
import com.personalrchabane.videogame_library_backend.dto.game.out.GameOutDTO;

import java.util.List;

public interface GameService {
    List<GameOutDTO> findAllGames();
    List<GameOutDTO> findFilteredAndSortedGames(String name, String genre, Integer releaseYear, String studioName, List<String> platforms, String sort);
    GameOutDTO saveGame(GameCreateDTO gameCreateDTO);
}