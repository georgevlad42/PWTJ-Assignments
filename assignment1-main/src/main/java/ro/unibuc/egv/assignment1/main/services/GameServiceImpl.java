package ro.unibuc.egv.assignment1.main.services;

import org.springframework.stereotype.Service;
import ro.unibuc.egv.assignment1.main.model.Game;
import ro.unibuc.egv.assignment1.main.repositories.GameRepository;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public void addGame(Game game) {
        gameRepository.add(new Game(game.getId(), game.getName(), game.getPrice()));
    }

    @Override
    public Set<Game> getAllGames() {
        return gameRepository.findAll().stream()
                .map(game -> new Game(game.getName(), game.getPrice()))
                .collect(Collectors.collectingAndThen(Collectors.toCollection(LinkedHashSet::new), Collections::unmodifiableSet));
    }
}
