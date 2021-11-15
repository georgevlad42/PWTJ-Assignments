package ro.unibuc.egv.assignment1.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.egv.assignment1.main.model.Game;
import ro.unibuc.egv.assignment1.main.services.GameService;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    //region Main page
    @RequestMapping("/")
    public String index(){
        System.out.println("Main page accessed!");
       return "index";
    }
    //endregion

    //region Create page
    @RequestMapping("/create")
    public String createInit(){
        System.out.println("Create page accessed!");
        return "create";
    }

    @PostMapping("/create")
    public String addGame(@RequestParam("nameToAdd") String name, @RequestParam("priceToAdd") int price, Model model){
        gameService.saveGame(gameService.getAllGames().size() !=0 ? new Game(String.valueOf(gameService.getAllGames().size() + 1), name, price) : new Game(String.valueOf(1), name, price));
        model.addAttribute("gameList", gameService.getAllGames());
        System.out.println("A new game was successfully added!");
        return "create";
    }
    //endregion

    //region Read page
    @RequestMapping("/read")
    @GetMapping("/read")
    public String readInit(Model model){
        System.out.println("Read page accessed!");
        model.addAttribute("gameList", gameService.getAllGames());
        return "read";
    }
    //endregion

    //region Update page
    @RequestMapping("/update")
    @GetMapping("/update")
    public String updateInit(Model model){
        System.out.println("Update page accessed!");
        model.addAttribute("gameList", gameService.getAllGames());
        return "update";
    }

    @PostMapping("/update")
    public String updateGame(@RequestParam("gameToUpdateID") String id, @RequestParam("nameToUpdate") String name, @RequestParam("priceToUpdate") int price, Model model){
        gameService.saveGame(new Game(id, name, price));
        model.addAttribute("gameList", gameService.getAllGames());
        System.out.println("Game with ID " + id + " was successfully updated!");
        return "update";
    }

    //endregion

    //region Delete page
    @RequestMapping("/delete")
    public String deleteInit(Model model){
        System.out.println("Delete page accessed!");
        model.addAttribute("gameList", gameService.getAllGames());
        return "delete";
    }

    @PostMapping("/delete")
    public String removeGame(@RequestParam("gameToRemoveID") String id, Model model){
        gameService.deleteGame(id);
        model.addAttribute("gameList", gameService.getAllGames());
        System.out.println("Game with ID " + id + " was successfully deleted!");
        return "delete";
    }
    //endregion
}
