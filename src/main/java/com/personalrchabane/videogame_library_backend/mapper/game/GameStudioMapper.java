package com.personalrchabane.videogame_library_backend.mapper.game;

import com.personalrchabane.videogame_library_backend.dto.game.in.GameStudioCreateDTO;
import com.personalrchabane.videogame_library_backend.dto.game.out.GameStudioOutDTO;
import com.personalrchabane.videogame_library_backend.model.game.GameStudio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameStudioMapper {
    GameStudioOutDTO toGameStudioOutDTO(GameStudio gameStudio);
    GameStudio toGameStudio(GameStudioCreateDTO gameStudioCreateDTO);
}
