package com.personalrchabane.videogame_library_backend.impl.game;

import com.personalrchabane.videogame_library_backend.dto.game.in.GameCreateDTO;
import com.personalrchabane.videogame_library_backend.dto.game.out.GameOutDTO;
import com.personalrchabane.videogame_library_backend.mapper.game.GameMapper;
import com.personalrchabane.videogame_library_backend.model.game.Platform;
import com.personalrchabane.videogame_library_backend.model.game.Game;
import com.personalrchabane.videogame_library_backend.repository.game.GameStudioRepository;
import com.personalrchabane.videogame_library_backend.repository.game.PlatformRepository;
import com.personalrchabane.videogame_library_backend.repository.game.GameRepository;
import com.personalrchabane.videogame_library_backend.service.game.GameService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final GameStudioRepository gameStudioRepository;
    private final PlatformRepository platformRepository;
    private final GameMapper gameMapper;

    public GameServiceImpl(GameRepository gameRepository, GameStudioRepository gameStudioRepository,
                           PlatformRepository platformRepository, GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.gameStudioRepository = gameStudioRepository;
        this.platformRepository = platformRepository;
        this.gameMapper = gameMapper;
    }

    @Override
    public List<GameOutDTO> findAllGames() {
        return gameRepository.findAll().stream()
                .map(gameMapper::toGameOutDTO)
                .toList();
    }

    /**
     * Retrieves a list of games filtered by the provided parameters and sorted by the specified field.
     *
     * @param name        the name of the game to filter by (optional)
     * @param genre       the genre of the game to filter by (optional)
     * @param releaseYear the release year of the game to filter by (optional)
     * @param studioName  the name of the studio to filter by (optional)
     * @param platforms   the list of platforms to filter by (optional)
     * @param sort        the sorting criteria in the format "field,direction" (e.g., "name,asc" or "releaseYear,desc")
     * @return a ResponseEntity containing a list of GameOutDTO objects
     *
     * Example URLs:
     * - /api/games?name=Cyberpunk 2077
     * - /api/games?genre=Action&sort=releaseYear,desc
     * - /api/games?releaseYear=2020&studioName=Nintendo
     * - /api/games?platforms=PC,PlayStation 4&sort=name,asc
     */
    @Override
    public List<GameOutDTO> findFilteredAndSortedGames(String name, String genre, Integer releaseYear, String studioName, List<String> platforms, String sort) {
        // Décomposer le tri
        String[] sortParams = sort.split(",");
        String sortField = sortParams[0];
        Sort.Direction direction = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        // Appliquer les filtres et le tri
        List<Game> games = gameRepository.findAll(Sort.by(direction, sortField)).stream()
                .filter(game -> name == null || game.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(game -> genre == null || game.getGenre().equalsIgnoreCase(genre))
                .filter(game -> releaseYear == null || game.getReleaseYear() == releaseYear)
                .filter(game -> studioName == null || game.getStudio().getName().equalsIgnoreCase(studioName))
                .filter(game -> platforms == null || game.getPlatforms().stream()
                        .anyMatch(platform -> platforms.contains(platform.getName())))
                .toList();

        // Convertir les entités en DTOs
        return games.stream().map(gameMapper::toGameOutDTO).toList();
    }

    @Override
    public GameOutDTO saveGame(GameCreateDTO gameCreateDTO) {
        Game game = gameMapper.toGame(gameCreateDTO);

        // Associer le studio
        game.setStudio(gameStudioRepository.findById(gameCreateDTO.getStudioId())
                .orElseThrow(() -> new IllegalArgumentException("Studio not found")));

        // Associer les plateformes
        List<Platform> platforms = platformRepository.findAllById(gameCreateDTO.getPlatformIds());
        game.setPlatforms(Set.copyOf(platforms));

        return gameMapper.toGameOutDTO(gameRepository.save(game));
    }
}
