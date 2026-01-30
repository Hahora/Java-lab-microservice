package com.example.gameservice.controller;

import com.example.gameservice.model.Game;
import com.example.gameservice.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

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