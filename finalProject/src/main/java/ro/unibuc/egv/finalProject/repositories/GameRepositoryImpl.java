package ro.unibuc.egv.finalProject.repositories;

import org.springframework.stereotype.Repository;
import ro.unibuc.egv.finalProject.models.Game;

import java.util.Map;

@Repository
public class GameRepositoryImpl implements GameRepository {
    @Override
    public void save(Game game) {

    }

    @Override
    public Map<String, Game> findAll() {
        return null;
    }

    @Override
    public void update(Game game) {

    }

    @Override
    public void delete(Game game) {

    }
}
