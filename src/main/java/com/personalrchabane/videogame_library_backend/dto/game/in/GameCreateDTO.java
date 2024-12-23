package com.personalrchabane.videogame_library_backend.dto.game.in;

import lombok.Data;

import java.util.Set;

@Data
public class GameCreateDTO {
    private String name;
    private String genre;
    private int releaseYear;
    private Long studioId;
    private Set<Long> platformIds;
}
