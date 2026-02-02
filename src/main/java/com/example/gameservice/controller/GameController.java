package com.example.gameservice.controller;

import com.example.gameservice.config.GameConfig;
import com.example.gameservice.model.Game;
import com.example.gameservice.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameConfig gameConfig;

    @Value("${greeting.message:Привет!}")
    private String greetingMessage;

    @Value("${app.name:Game Service}")
    private String appName;

    @GetMapping("/config")
    public ResponseEntity<String> getConfig() {
        String configInfo = String.format(
                "Приложение: %s\n" +
                        "Сообщение: %s\n" +
                        "Конфигурация игр: %s",
                appName, greetingMessage, gameConfig.toString()
        );
        return ResponseEntity.ok(configInfo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable Long id) {
        Game game = gameService.getGameById(id);
        return game != null ? ResponseEntity.ok(game) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        return ResponseEntity.ok(gameService.createGame(game));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game game) {
        return ResponseEntity.ok(gameService.updateGame(id, game));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.deleteGame(id));
    }
}