package com.personalrchabane.videogame_library_backend.repository.game;

import com.personalrchabane.videogame_library_backend.model.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long>, JpaSpecificationExecutor<Game> {

    @Query("SELECT DISTINCT g.releaseYear FROM Game g ORDER BY g.releaseYear ASC")
    List<Integer> findAllUniqueReleaseYears();

    @Query("SELECT DISTINCT g.genre FROM Game g ORDER BY g.genre ASC")
    List<String> findAllUniqueGenres();
}
