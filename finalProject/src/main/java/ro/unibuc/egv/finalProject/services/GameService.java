package ro.unibuc.egv.finalProject.services;

import ro.unibuc.egv.finalProject.models.Game;

import java.util.Map;

public interface GameService {

    void addGame (Game game);
    Map<String, Game> showGames();
    void updateGame (Game game);
    void deleteGame (Game game);

}
