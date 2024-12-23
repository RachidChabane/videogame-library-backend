package com.personalrchabane.videogame_library_backend.model.game;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String genre;

    private int releaseYear;

    @ManyToOne
    @JoinColumn(name = "studio_id", nullable = false)
    private GameStudio studio;

    @ManyToMany
    @JoinTable(
            name = "game_platform",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private Set<Platform> platforms;

    public Game(String name, String type, int releaseYear, GameStudio gameStudio, Set<Platform> platforms) {
        this.name = name;
        this.genre = type;
        this.releaseYear = releaseYear;
        this.studio = gameStudio;
        this.platforms = platforms;
    }
}
