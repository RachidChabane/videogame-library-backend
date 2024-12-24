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
import java.util.Optional;
import java.util.Set;

@Component
// Entièrement généré par ChatGPT parce qu'on ne va pas perdre son temps avec ça quand même
public class DataInitializer {

    private final GameRepository gameRepository;
    private final GameStudioRepository gameStudioRepository;
    private final PlatformRepository platformRepository;

    public DataInitializer(GameRepository gameRepository,
                           GameStudioRepository gameStudioRepository,
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

        // -- PLATEFORMES EXISTANTES --
        Platform pc = platformRepository.save(new Platform("PC"));
        Platform ps4 = platformRepository.save(new Platform("PlayStation 4"));
        Platform ps5 = platformRepository.save(new Platform("PlayStation 5"));
        Platform xboxOne = platformRepository.save(new Platform("Xbox One"));
        Platform xboxOneS = platformRepository.save(new Platform("Xbox One S"));
        Platform nintendoSwitch = platformRepository.save(new Platform("Nintendo Switch"));
        Platform stadia = platformRepository.save(new Platform("Stadia"));

        // -- NOUVELLES PLATEFORMES (Retro, etc.) --
        Platform ps2 = platformRepository.save(new Platform("PlayStation 2"));
        Platform ps3 = platformRepository.save(new Platform("PlayStation 3"));
        Platform wii = platformRepository.save(new Platform("Wii"));
        Platform gameCube = platformRepository.save(new Platform("GameCube"));
        Platform n64 = platformRepository.save(new Platform("Nintendo 64"));
        Platform ds = platformRepository.save(new Platform("Nintendo DS"));
        // Vous pouvez en ajouter d’autres si nécessaire (Game Boy, SNES, etc.).

        // -- STUDIOS EXISTANTS --
        GameStudio cdProjektRed = gameStudioRepository.save(new GameStudio("CD Projekt Red"));
        GameStudio naughtyDog = gameStudioRepository.save(new GameStudio("Naughty Dog"));
        GameStudio rockstarGames = gameStudioRepository.save(new GameStudio("Rockstar Games"));
        GameStudio ubisoft = gameStudioRepository.save(new GameStudio("Ubisoft"));
        GameStudio bethesda = gameStudioRepository.save(new GameStudio("Bethesda Game Studios"));

        // -- NOUVEAUX STUDIOS --
        GameStudio nintendo = gameStudioRepository.save(new GameStudio("Nintendo"));
        GameStudio squareEnix = gameStudioRepository.save(new GameStudio("Square Enix"));
        GameStudio capcom = gameStudioRepository.save(new GameStudio("Capcom"));
        GameStudio konami = gameStudioRepository.save(new GameStudio("Konami"));
        GameStudio sega = gameStudioRepository.save(new GameStudio("Sega"));

        // -- JEUX --
        // Jeux déjà présents dans l’exemple
        List<Game> games = List.of(
                new Game("The Witcher 3: Wild Hunt", "RPG", 2015, cdProjektRed,
                        Set.of(pc, ps4, ps5, xboxOne, xboxOneS, nintendoSwitch)),
                new Game("The Last of Us Part II", "Action-adventure", 2020, naughtyDog,
                        Set.of(ps4, ps5)),
                new Game("Cyberpunk 2077", "RPG", 2020, cdProjektRed,
                        Set.of(pc, ps4, ps5, xboxOne, xboxOneS, stadia)),
                new Game("Red Dead Redemption 2", "Action-adventure", 2018, rockstarGames,
                        Set.of(pc, ps4, ps5, xboxOne, xboxOneS)),
                new Game("Grand Theft Auto V", "Action-adventure", 2013, rockstarGames,
                        Set.of(pc, ps4, ps5, xboxOne, xboxOneS)),
                new Game("Assassin's Creed Valhalla", "Action RPG", 2020, ubisoft,
                        Set.of(pc, ps4, ps5, xboxOne, xboxOneS)),
                new Game("The Elder Scrolls V: Skyrim", "RPG", 2011, bethesda,
                        Set.of(pc, ps4, ps5, xboxOne, xboxOneS, nintendoSwitch)),
                new Game("Far Cry 5", "Action-adventure", 2018, ubisoft,
                        Set.of(pc, ps4, xboxOne, xboxOneS)),
                new Game("Doom Eternal", "First-person shooter", 2020, bethesda,
                        Set.of(pc, ps4, ps5, xboxOne, xboxOneS)),
                new Game("Horizon Zero Dawn", "Action RPG", 2017, naughtyDog,
                        Set.of(pc, ps4, ps5))
        );

        // -- NOUVEAUX JEUX (rétro, récents, etc.) --
        List<Game> additionalGames = List.of(
                // Quelques classiques Nintendo 64
                new Game("Super Mario 64", "Platform", 1996, nintendo,
                        Set.of(n64)),
                new Game("The Legend of Zelda: Ocarina of Time", "Action-adventure", 1998, nintendo,
                        Set.of(n64)),
                new Game("GoldenEye 007", "First-person shooter", 1997, nintendo,
                        Set.of(n64)),

                // GameCube & Wii
                new Game("Super Mario Sunshine", "Platform", 2002, nintendo,
                        Set.of(gameCube)),
                new Game("Metroid Prime", "Action-adventure", 2002, nintendo,
                        Set.of(gameCube)),
                new Game("The Legend of Zelda: The Wind Waker", "Action-adventure", 2003, nintendo,
                        Set.of(gameCube)),
                new Game("The Legend of Zelda: Twilight Princess", "Action-adventure", 2006, nintendo,
                        Set.of(gameCube, wii)),
                new Game("Mario Kart: Double Dash!!", "Racing", 2003, nintendo,
                        Set.of(gameCube)),
                new Game("Super Smash Bros. Melee", "Fighting", 2001, nintendo,
                        Set.of(gameCube)),
                new Game("Wii Sports", "Sports", 2006, nintendo,
                        Set.of(wii)),
                new Game("Super Mario Galaxy", "Platform", 2007, nintendo,
                        Set.of(wii)),
                new Game("Metroid Prime 3: Corruption", "Action-adventure", 2007, nintendo,
                        Set.of(wii)),

                // PlayStation 2 (classiques)
                new Game("Final Fantasy X", "RPG", 2001, squareEnix,
                        Set.of(ps2)),
                new Game("Kingdom Hearts", "Action RPG", 2002, squareEnix,
                        Set.of(ps2)),
                new Game("Metal Gear Solid 3: Snake Eater", "Stealth", 2004, konami,
                        Set.of(ps2)),
                new Game("Shadow of the Colossus", "Action-adventure", 2005, sonyInteractive("Team Ico"), // Studio parfois listé sous Sony/Team Ico
                        Set.of(ps2)),
                new Game("ICO", "Action-adventure", 2001, sonyInteractive("Team Ico"),
                        Set.of(ps2)),
                new Game("Resident Evil 4", "Survival horror", 2005, capcom,
                        Set.of(ps2, gameCube, wii, ps4, xboxOne, nintendoSwitch)),
                new Game("Okami", "Action-adventure", 2006, capcom,
                        Set.of(ps2, wii)),

                // PlayStation 3
                new Game("The Last of Us", "Action-adventure", 2013, naughtyDog,
                        Set.of(ps3, ps4)),
                new Game("Uncharted 2: Among Thieves", "Action-adventure", 2009, naughtyDog,
                        Set.of(ps3)),
                new Game("Metal Gear Solid 4: Guns of the Patriots", "Stealth", 2008, konami,
                        Set.of(ps3)),
                new Game("Final Fantasy XIII", "RPG", 2009, squareEnix,
                        Set.of(ps3, xboxOne)),

                // Sega
                new Game("Sonic the Hedgehog", "Platform", 1991, sega,
                        Set.of()), // Retro, Megadrive / Genesis
                new Game("Sonic Adventure 2", "Platform", 2001, sega,
                        Set.of(gameCube, ps3)), // Sur Dreamcast à l’origine, ici on simplifie

                // Nintendo DS
                new Game("New Super Mario Bros.", "Platform", 2006, nintendo,
                        Set.of(ds)),
                new Game("Mario Kart DS", "Racing", 2005, nintendo,
                        Set.of(ds)),
                new Game("Pokémon Diamond", "RPG", 2006, nintendo,
                        Set.of(ds)),
                new Game("Animal Crossing: Wild World", "Social simulation", 2005, nintendo,
                        Set.of(ds)),

                // Autres classiques ou jeux rétro divers
                new Game("Castlevania: Symphony of the Night", "Action-adventure", 1997, konami,
                        Set.of()), // Originalement sur PS1/Saturn
                new Game("Street Fighter II", "Fighting", 1991, capcom,
                        Set.of()), // SNES/Arcade
                new Game("Mega Man X", "Action-platform", 1993, capcom,
                        Set.of()), // SNES
                new Game("Chrono Trigger", "RPG", 1995, squareEnix,
                        Set.of()), // SNES

                // Quelques titres plus récents / cross-platform
                new Game("Monster Hunter World", "Action RPG", 2018, capcom,
                        Set.of(pc, ps4, xboxOne)),
                new Game("Devil May Cry 5", "Action-adventure", 2019, capcom,
                        Set.of(pc, ps4, ps5, xboxOne, xboxOneS)),
                new Game("Final Fantasy VII Remake", "Action RPG", 2020, squareEnix,
                        Set.of(ps4, ps5)),
                new Game("Dragon Quest XI S: Echoes of an Elusive Age", "RPG", 2017, squareEnix,
                        Set.of(pc, ps4, xboxOne, nintendoSwitch)),
                new Game("Bayonetta", "Action-adventure", 2009, sega,
                        Set.of(pc, ps3, xboxOne, nintendoSwitch, wii)),

                // Quelques autres pour atteindre ~50
                new Game("Resident Evil 2 (Remake)", "Survival horror", 2019, capcom,
                        Set.of(pc, ps4, ps5, xboxOne, xboxOneS)),
                new Game("Ghost of Tsushima", "Action-adventure", 2020, sonyInteractive("Sucker Punch"),
                        Set.of(ps4, ps5)),
                new Game("God of War (2018)", "Action-adventure", 2018, sonyInteractive("Santa Monica Studio"),
                        Set.of(ps4, ps5)),
                new Game("Halo: Combat Evolved", "First-person shooter", 2001, microsoftStudio("Bungie"),
                        Set.of(xboxOne)), // Originalement Xbox, simplifié
                new Game("Bloodborne", "Action RPG", 2015, sonyInteractive("FromSoftware"),
                        Set.of(ps4)),
                new Game("The Legend of Zelda: Breath of the Wild", "Action-adventure", 2017, nintendo,
                        Set.of(nintendoSwitch, wii))
        );

        // On fusionne les deux listes
        List<Game> allGames = new java.util.ArrayList<>(games);
        allGames.addAll(additionalGames);

        // Sauvegarde de tous les jeux
        gameRepository.saveAll(allGames);

        System.out.println("Données initialisées avec succès !");
    }

    /**
     * Méthode utilitaire pour simuler un studio (ex.: Sony, Sucker Punch, Team Ico, etc.)
     */
    private GameStudio sonyInteractive(String subStudioName) {
        return subStudio("Sony Interactive", subStudioName);
    }


    /**
     * Méthode utilitaire pour simuler un studio (ex.: Microsoft, Bungie, etc.)
     */
    private GameStudio microsoftStudio(String subStudioName) {
        return subStudio("Microsoft", subStudioName);
    }

    private GameStudio subStudio(String studioName, String subStudioName) {
        String fullStudioName = studioName + " - " + subStudioName;
        // Tente de récupérer en base
        Optional<GameStudio> existingStudio = gameStudioRepository.findByName(fullStudioName);
        // Crée et sauve en base s’il n’existe pas
        return existingStudio.orElseGet(() -> gameStudioRepository.save(new GameStudio(fullStudioName)));
    }
}
