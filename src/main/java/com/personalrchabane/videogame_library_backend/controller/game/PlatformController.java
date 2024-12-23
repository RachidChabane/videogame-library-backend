package com.personalrchabane.videogame_library_backend.controller.game;

import com.personalrchabane.videogame_library_backend.dto.game.in.PlatformCreateDTO;
import com.personalrchabane.videogame_library_backend.dto.game.out.PlatformOutDTO;
import com.personalrchabane.videogame_library_backend.service.game.PlatformService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/platforms")
public class PlatformController {
    private final PlatformService platformService;

    public PlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping
    public List<PlatformOutDTO> getAllPlatforms() {
        return platformService.findAllPlatforms();
    }

    @PostMapping
    public ResponseEntity<PlatformOutDTO> createPlatform(@RequestBody PlatformCreateDTO platformCreateDTO) {
        return ResponseEntity.ok(platformService.savePlatform(platformCreateDTO));
    }
}
