package ro.unibuc.egv.assignment1.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.egv.assignment1.main.model.Game;
import ro.unibuc.egv.assignment1.main.services.GameService;

@Controller
@RequestMapping("/")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String getAllGames(Model model){
        model.addAttribute("gameList", gameService.getAllGames());
        return "index";
    }

    @PostMapping("/")
    public String addGame(@RequestParam("nameToAdd") String name, @RequestParam("priceToAdd") int price, Model model){
        gameService.addGame(new Game(String.valueOf(gameService.getAllGames().size() + 1), name, price));
        model.addAttribute("gameList", gameService.getAllGames());
        return "index";
    }

//    @PostMapping("/")
//    public void updateGameName(@RequestParam("selectedGame") Game game, @RequestParam("nameForUpdate") String name){
//        gameRepository.updateName(game, name);
//    }
//
//    @PostMapping("/")
//    public void updateGamePrice(@RequestParam("selectedGame") Game game, @RequestParam("priceForUpdate") int price){
//        gameRepository.updatePrice(game, price);
//    }
//
//    @DeleteMapping("/")
//    public void removeGame(@RequestParam("selectedGame") Game game){
//        gameRepository.delete(game.getId());
//    }
}
