package com.personalrchabane.videogame_library_backend.impl.game;

import com.personalrchabane.videogame_library_backend.dto.game.in.GameCreateDTO;
import com.personalrchabane.videogame_library_backend.dto.game.out.GameOutDTO;
import com.personalrchabane.videogame_library_backend.mapper.game.GameMapper;
import com.personalrchabane.videogame_library_backend.model.game.GameStudio;
import com.personalrchabane.videogame_library_backend.model.game.Platform;
import com.personalrchabane.videogame_library_backend.model.game.Game;
import com.personalrchabane.videogame_library_backend.repository.game.GameStudioRepository;
import com.personalrchabane.videogame_library_backend.repository.game.PlatformRepository;
import com.personalrchabane.videogame_library_backend.repository.game.GameRepository;
import com.personalrchabane.videogame_library_backend.service.game.GameService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<GameOutDTO> findFilteredAndSortedGames(String name, String genre, Integer releaseYear, String studioName, List<String> platforms, String sort, Pageable pageable) {
        // Parser les param de sorting
        String[] sortParams = sort.split(",");
        String sortField = sortParams[0];
        String sortDirection = sortParams.length > 1 ? sortParams[1] : "asc";

        // Créer l'objet de sorting
        Sort.Direction direction = sortDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sorting = Sort.by(direction, sortField);

        // Appliquer le sorting au pageable
        Pageable pageableWithSort = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sorting);

        // Appliquer les filtres de manière dynamique
        Page<Game> filteredGames = gameRepository.findAll((root, query, cb) -> {
            var predicates = cb.conjunction();

            if (name != null) {
                predicates = cb.and(predicates, cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (genre != null) {
                predicates = cb.and(predicates, cb.equal(cb.lower(root.get("genre")), genre.toLowerCase()));
            }
            if (releaseYear != null) {
                predicates = cb.and(predicates, cb.equal(root.get("releaseYear"), releaseYear));
            }
            if (studioName != null) {
                predicates = cb.and(predicates, cb.equal(cb.lower(root.get("studio").get("name")), studioName.toLowerCase()));
            }
            if (platforms != null && !platforms.isEmpty()) {
                predicates = cb.and(predicates, root.join("platforms").get("name").in(platforms));
            }

            return predicates;
        }, pageableWithSort); // Utiliser le pageable avec le sorting

        return filteredGames.map(gameMapper::toGameOutDTO);
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

    @Override
    public GameOutDTO updateGame(Long id, GameCreateDTO gameCreateDTO) {
        // Vérifie si le jeu existe
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Game with ID " + id + " not found"));

        // Met à jour les données du jeu
        game.setName(gameCreateDTO.getName());
        game.setGenre(gameCreateDTO.getGenre());
        game.setReleaseYear(gameCreateDTO.getReleaseYear());

        // Associe le studio
        GameStudio studio = gameStudioRepository.findById(gameCreateDTO.getStudioId())
                .orElseThrow(() -> new IllegalArgumentException("Studio with ID " + gameCreateDTO.getStudioId() + " not found"));
        game.setStudio(studio);

        // Associe les plateformes
        List<Platform> platforms = platformRepository.findAllById(gameCreateDTO.getPlatformIds());
        if (platforms.size() != gameCreateDTO.getPlatformIds().size()) {
            throw new IllegalArgumentException("One or more platforms not found");
        }
        game.setPlatforms(Set.copyOf(platforms));

        // Sauvegarde et retourne le DTO
        return gameMapper.toGameOutDTO(gameRepository.save(game));
    }

    @Override
    public void deleteGame(Long id) {
        // Vérifie si le jeu existe
        if (!gameRepository.existsById(id)) {
            throw new IllegalArgumentException("Game with ID " + id + " not found");
        }
        gameRepository.deleteById(id);
    }

    @Override
    public List<Integer> findAllUniqueReleaseYears() {
        return gameRepository.findAllUniqueReleaseYears();
    }

    @Override
    public List<String> findAllUniqueGenres() {
        return gameRepository.findAllUniqueGenres();
    }
}
