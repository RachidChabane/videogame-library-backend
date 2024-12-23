package com.personalrchabane.videogame_library_backend.mapper.game;

import com.personalrchabane.videogame_library_backend.dto.game.in.PlatformCreateDTO;
import com.personalrchabane.videogame_library_backend.dto.game.out.PlatformOutDTO;
import com.personalrchabane.videogame_library_backend.model.game.Platform;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlatformMapper {
    PlatformOutDTO toPlatformOutDTO(Platform platform);
    Platform toPlatform(PlatformCreateDTO platformCreateDTO);
}
