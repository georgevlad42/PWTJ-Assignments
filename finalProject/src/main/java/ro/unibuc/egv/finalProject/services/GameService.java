package ro.unibuc.egv.finalProject.services;

import org.springframework.stereotype.Service;
import ro.unibuc.egv.finalProject.models.Game;
import ro.unibuc.egv.finalProject.repositories.GameRepository;

import java.util.List;

@Service
public class GameService {

    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game getGameByID(Long id){
        return gameRepository.findGameByGameID(id);
    }

    public List<Game> getGames(){
        return gameRepository.findAll();
    }

    public void updateGameQuantity(Game game){
        gameRepository.save(game);
    }

}
