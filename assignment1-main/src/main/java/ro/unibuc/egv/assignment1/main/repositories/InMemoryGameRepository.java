package ro.unibuc.egv.assignment1.main.repositories;

import org.springframework.stereotype.Repository;
import ro.unibuc.egv.assignment1.main.model.Game;

import java.util.ArrayList;
import java.util.List;

@Repository
public final class InMemoryGameRepository implements GameRepository {

    private final List<Game> gameList = new ArrayList<>();
    private int idCounter;

    @Override
    public Game add(Game game) {
        final var storedGame = new Game(++idCounter, game.getName(), game.getPrice());
        gameList.add(game);
        return storedGame;
    }

    @Override
    public List<Game> findAll() {
        return List.copyOf(gameList);
    }
}
