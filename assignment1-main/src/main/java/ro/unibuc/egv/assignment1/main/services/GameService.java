package ro.unibuc.egv.assignment1.main.services;

import ro.unibuc.egv.assignment1.main.model.Game;

import java.util.Set;

public interface GameService {

    void saveGame(Game game);
    Set<Game> getAllGames();
    void deleteGame (String id);

}
