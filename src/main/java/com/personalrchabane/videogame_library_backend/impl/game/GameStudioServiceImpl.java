package com.personalrchabane.videogame_library_backend.impl.game;

import com.personalrchabane.videogame_library_backend.dto.game.in.GameStudioCreateDTO;
import com.personalrchabane.videogame_library_backend.dto.game.out.GameStudioOutDTO;
import com.personalrchabane.videogame_library_backend.mapper.game.GameStudioMapper;
import com.personalrchabane.videogame_library_backend.model.game.GameStudio;
import com.personalrchabane.videogame_library_backend.repository.game.GameStudioRepository;
import com.personalrchabane.videogame_library_backend.service.game.GameStudioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameStudioServiceImpl implements GameStudioService {

    private final GameStudioRepository gameStudioRepository;
    private final GameStudioMapper gameStudioMapper;

    public GameStudioServiceImpl(GameStudioRepository gameStudioRepository, GameStudioMapper gameStudioMapper) {
        this.gameStudioRepository = gameStudioRepository;
        this.gameStudioMapper = gameStudioMapper;
    }

    @Override
    public List<GameStudioOutDTO> findAllStudios() {
        return gameStudioRepository.findAll()
                .stream()
                .map(gameStudioMapper::toGameStudioOutDTO)
                .toList();
    }

    @Override
    public GameStudioOutDTO saveStudio(GameStudioCreateDTO studioCreateDTO) {
        GameStudio studio = gameStudioMapper.toGameStudio(studioCreateDTO);
        return gameStudioMapper.toGameStudioOutDTO(gameStudioRepository.save(studio));
    }
}
