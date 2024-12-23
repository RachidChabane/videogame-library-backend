package com.personalrchabane.videogame_library_backend.impl.game;

import com.personalrchabane.videogame_library_backend.dto.game.in.PlatformCreateDTO;
import com.personalrchabane.videogame_library_backend.dto.game.out.PlatformOutDTO;
import com.personalrchabane.videogame_library_backend.mapper.game.PlatformMapper;
import com.personalrchabane.videogame_library_backend.model.game.Platform;
import com.personalrchabane.videogame_library_backend.repository.game.PlatformRepository;
import com.personalrchabane.videogame_library_backend.service.game.PlatformService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformServiceImpl implements PlatformService {

    private final PlatformRepository platformRepository;
    private final PlatformMapper platformMapper;

    public PlatformServiceImpl(PlatformRepository platformRepository, PlatformMapper platformMapper) {
        this.platformRepository = platformRepository;
        this.platformMapper = platformMapper;
    }

    @Override
    public List<PlatformOutDTO> findAllPlatforms() {
        return platformRepository.findAll()
                .stream()
                .map(platformMapper::toPlatformOutDTO)
                .toList();
    }

    @Override
    public PlatformOutDTO savePlatform(PlatformCreateDTO platformCreateDTO) {
        Platform platform = platformMapper.toPlatform(platformCreateDTO);
        return platformMapper.toPlatformOutDTO(platformRepository.save(platform));
    }
}
