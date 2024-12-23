package com.personalrchabane.videogame_library_backend.service.game;

import com.personalrchabane.videogame_library_backend.dto.game.in.GameStudioCreateDTO;
import com.personalrchabane.videogame_library_backend.dto.game.out.GameStudioOutDTO;

import java.util.List;

public interface GameStudioService {
    List<GameStudioOutDTO> findAllStudios();
    GameStudioOutDTO saveStudio(GameStudioCreateDTO studioCreateDTO);
}
