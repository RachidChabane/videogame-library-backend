package com.personalrchabane.videogame_library_backend.mapper.game;

import com.personalrchabane.videogame_library_backend.dto.game.in.GameCreateDTO;
import com.personalrchabane.videogame_library_backend.dto.game.out.GameOutDTO;
import com.personalrchabane.videogame_library_backend.model.game.Platform;
import com.personalrchabane.videogame_library_backend.model.game.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.Mapping;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GameMapper {

    @Mapping(source = "studio.name", target = "studioName")
    @Mapping(source = "platforms", target = "platforms", qualifiedByName = "platformNames")
    GameOutDTO toGameOutDTO(Game game);

    @Mapping(source = "studioId", target = "studio.id")
    @Mapping(source = "platformIds", target = "platforms", ignore = true)
    Game toGame(GameCreateDTO dto);

    @Named("platformNames")
    static Set<String> mapPlatformNames(Set<Platform> platforms) {
        return platforms.stream().map(Platform::getName).collect(Collectors.toSet());
    }
}
