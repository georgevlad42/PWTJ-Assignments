package ro.unibuc.egv.assignment1.main.services;

import org.springframework.stereotype.Service;
import ro.unibuc.egv.assignment1.main.model.Game;
import ro.unibuc.egv.assignment1.main.repositories.GameRepository;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public void saveGame(Game game) {
        gameRepository.save(game);
    }

    @Override
    public Set<Game> getAllGames() {
        return new HashSet<>(gameRepository.findAll().values());
    }

    @Override
    public void deleteGame(String id) {
        gameRepository.delete(id);
    }
}
