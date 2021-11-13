package ro.unibuc.egv.assignment1.main.repositories;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ro.unibuc.egv.assignment1.main.model.Game;

import java.util.List;
import java.util.Map;

@Repository
public class GameRepositoryImpl implements GameRepository {

    private final RedisTemplate<String,Game> redisTemplate;

    public GameRepositoryImpl(RedisTemplate<String, Game> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void add(Game game) {
        redisTemplate.opsForHash().put("Game",game.getId(),game);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Game> findAll() {
        return (List<Game>)(List<?>)List.copyOf(redisTemplate.opsForHash().entries("Game").values());
    }

//    @Override
//    public void updateName(Game game, String name) {
//        game.setName(name);
//        save(game);
//    }
//
//    @Override
//    public void updatePrice(Game game, int price) {
//        game.setPrice(price);
//        save(game);
//    }
//
//    @Override
//    public void delete(String id) {
//        redisTemplate.opsForHash().delete("Game",id);
//    }
}
