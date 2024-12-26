package com.personalrchabane.videogame_library_backend.dto.game.in;

import lombok.Data;

@Data
public class GameUpdateDTO {
    private String name;
    private String genre;
    private int releaseYear;
}
