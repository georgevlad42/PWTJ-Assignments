package ro.unibuc.egv.finalProject.repositories;

import ro.unibuc.egv.finalProject.models.Game;

import java.util.Map;

public interface GameRepository {

    void save (Game game);
    Map<String, Game> findAll();
    void update (Game game);
    void delete (Game game);

}
