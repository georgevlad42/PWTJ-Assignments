package ro.unibuc.egv.assignment1.main.repositories;

import ro.unibuc.egv.assignment1.main.model.Game;

import java.util.List;
import java.util.Map;

public interface GameRepository {

    void add (Game game);
    List<Game> findAll();
//    Map<Object, Object> findByName(String name);
//    void updateName(Game game, String name);
//    void updatePrice(Game game, int price);
//    void delete(String id);

}
