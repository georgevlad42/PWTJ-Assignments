package ro.unibuc.egv.finalProject.services;

import org.springframework.stereotype.Service;
import ro.unibuc.egv.finalProject.models.Game;
import ro.unibuc.egv.finalProject.repositories.GameRepository;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    //region Getters
    public Game getGameByID(Long id){
        return gameRepository.findGameByGameID(id);
    }

    public List<Game> getGames(){
        return gameRepository.findAll();
    }
    //endregion

    //region Status Update
    public void updateGameStatus(Game game){
        gameRepository.save(game);
    }
    //endregion

    //region Add Game
    public void addGame(Game game){
        game.getProduct().setStatus("Available");
        gameRepository.save(game);
    }
    //endregion

    //region Edit Game
    public void editGame(Game game){
        if (game.getProduct().getQuantity() > 0) {
            game.getProduct().setStatus("Available");
        } else {
            game.getProduct().setStatus("Unavailable");
        }
        gameRepository.save(game);
    }
    //endregion

    //region Delete Game
    public void deleteGame(Long id){
        gameRepository.deleteById(id);
    }
    //endregion

}
