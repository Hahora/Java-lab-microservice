package com.example.gameservice.service;

import com.example.gameservice.model.Game;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GameService {
    private final Map<Long, Game> games = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public Game getGameById(Long id) {
        return games.get(id);
    }

    public Game createGame(Game game) {
        long newId = counter.incrementAndGet();
        game.setId(newId);
        games.put(game.getId(), game);
        return game;
    }

    public Game updateGame(Long id, Game updatedGame) {
        updatedGame.setId(id);
        games.put(id, updatedGame);
        return updatedGame;
    }

    public String deleteGame(Long id) {
        games.remove(id);
        return String.format("Игра с id %d удалена", id);
    }
}
