package com.personalrchabane.videogame_library_backend.repository.game;

import com.personalrchabane.videogame_library_backend.model.game.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
}
