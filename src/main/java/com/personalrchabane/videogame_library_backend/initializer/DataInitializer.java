package com.personalrchabane.videogame_library_backend.initializer;

import com.personalrchabane.videogame_library_backend.model.game.Game;
import com.personalrchabane.videogame_library_backend.model.game.GameStudio;
import com.personalrchabane.videogame_library_backend.model.game.Platform;
import com.personalrchabane.videogame_library_backend.repository.game.GameRepository;
import com.personalrchabane.videogame_library_backend.repository.game.GameStudioRepository;
import com.personalrchabane.videogame_library_backend.repository.game.PlatformRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DataInitializer {

    private final GameRepository gameRepository;
    private final GameStudioRepository gameStudioRepository;
    private final PlatformRepository platformRepository;

    public DataInitializer(GameRepository gameRepository, GameStudioRepository gameStudioRepository,
                           PlatformRepository platformRepository) {
        this.gameRepository = gameRepository;
        this.gameStudioRepository = gameStudioRepository;
        this.platformRepository = platformRepository;
    }

    @PostConstruct
    public void initData() {
        // Vérifie si les données existent déjà
        if (!gameRepository.findAll().isEmpty()) {
            return;
        }

        // Plateformes
        Platform pc = platformRepository.save(new Platform("PC"));
        Platform ps4 = platformRepository.save(new Platform("PlayStation 4"));
        Platform ps5 = platformRepository.save(new Platform("PlayStation 5"));
        Platform xboxOne = platformRepository.save(new Platform("Xbox One"));
        Platform xboxOneS = platformRepository.save(new Platform("Xbox One S"));
        Platform nintendoSwitch = platformRepository.save(new Platform("Nintendo Switch"));
        Platform stadia = platformRepository.save(new Platform("Stadia"));

        // Studios
        GameStudio cdProjektRed = gameStudioRepository.save(new GameStudio("CD Projekt Red"));
        GameStudio naughtyDog = gameStudioRepository.save(new GameStudio("Naughty Dog"));
        GameStudio rockstarGames = gameStudioRepository.save(new GameStudio("Rockstar Games"));
        GameStudio ubisoft = gameStudioRepository.save(new GameStudio("Ubisoft"));
        GameStudio bethesda = gameStudioRepository.save(new GameStudio("Bethesda Game Studios"));

        // Jeux
        gameRepository.saveAll(List.of(
                new Game("The Witcher 3: Wild Hunt", "RPG", 2015, cdProjektRed, Set.of(pc, ps4, ps5, xboxOne, xboxOneS, nintendoSwitch)),
                new Game("The Last of Us Part II", "Action-adventure", 2020, naughtyDog, Set.of(ps4, ps5)),
                new Game("Cyberpunk 2077", "RPG", 2020, cdProjektRed, Set.of(pc, ps4, ps5, xboxOne, xboxOneS, stadia)),
                new Game("Red Dead Redemption 2", "Action-adventure", 2018, rockstarGames, Set.of(pc, ps4, ps5, xboxOne, xboxOneS)),
                new Game("Grand Theft Auto V", "Action-adventure", 2013, rockstarGames, Set.of(pc, ps4, ps5, xboxOne, xboxOneS)),
                new Game("Assassin's Creed Valhalla", "Action RPG", 2020, ubisoft, Set.of(pc, ps4, ps5, xboxOne, xboxOneS)),
                new Game("The Elder Scrolls V: Skyrim", "RPG", 2011, bethesda, Set.of(pc, ps4, ps5, xboxOne, xboxOneS, nintendoSwitch)),
                new Game("Far Cry 5", "Action-adventure", 2018, ubisoft, Set.of(pc, ps4, xboxOne, xboxOneS)),
                new Game("Doom Eternal", "First-person shooter", 2020, bethesda, Set.of(pc, ps4, ps5, xboxOne, xboxOneS)),
                new Game("Horizon Zero Dawn", "Action RPG", 2017, naughtyDog, Set.of(pc, ps4, ps5))
        ));

        System.out.println("Données initialisées avec succès !");
    }
}