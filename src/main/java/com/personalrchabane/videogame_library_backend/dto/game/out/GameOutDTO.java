package com.personalrchabane.videogame_library_backend.dto.game.out;

import lombok.Data;

import java.util.Set;

@Data
public class GameOutDTO {
    private Long id;
    private String name;
    private String genre;
    private int releaseYear;
    private String studioName;
    private Set<String> platforms;
}
