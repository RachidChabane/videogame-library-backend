package com.personalrchabane.videogame_library_backend.service.game;

import com.personalrchabane.videogame_library_backend.dto.game.in.PlatformCreateDTO;
import com.personalrchabane.videogame_library_backend.dto.game.out.PlatformOutDTO;

import java.util.List;

public interface PlatformService {
    List<PlatformOutDTO> findAllPlatforms();
    PlatformOutDTO savePlatform(PlatformCreateDTO platformCreateDTO);
}
