package com.personalrchabane.videogame_library_backend.repository.game;

import com.personalrchabane.videogame_library_backend.model.game.GameStudio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameStudioRepository extends JpaRepository<GameStudio, Long> {
    Optional<GameStudio> findByName(String studioName);
}
