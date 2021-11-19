package ro.unibuc.egv.assignment1.main.repositories;

import ro.unibuc.egv.assignment1.main.model.Game;

import java.util.Map;

public interface GameRepository {

    void save (Game game); // for the Create and Update options
    Map<String, Game> findAll(); // for the Read option
    void delete(String id); // for the Delete option

}
