package com.personalrchabane.videogame_library_backend.repository.game;

import com.personalrchabane.videogame_library_backend.model.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
