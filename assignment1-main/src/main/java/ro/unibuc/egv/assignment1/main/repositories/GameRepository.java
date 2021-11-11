package ro.unibuc.egv.assignment1.main.repositories;

import ro.unibuc.egv.assignment1.main.model.Game;

import java.util.List;

public interface GameRepository {

    Game add(Game game);

    List<Game> findAll();

}
