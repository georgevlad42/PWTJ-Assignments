package ro.unibuc.egv.assignment1.main.repositories;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ro.unibuc.egv.assignment1.main.model.Game;

import java.util.Map;

@Repository
public class GameRepositoryImpl implements GameRepository {

    private final RedisTemplate<String,Game> redisTemplate;

    public GameRepositoryImpl(RedisTemplate<String, Game> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(Game game) {
        redisTemplate.opsForHash().put("Game",game.getId(),game);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Game> findAll() {
        return (Map<String, Game>)(Map<?,?>) redisTemplate.opsForHash().entries("Game");
    }

    @Override
    public void delete(String id) {
        redisTemplate.opsForHash().delete("Game", id);
    }
}
