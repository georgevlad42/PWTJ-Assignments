package ro.unibuc.egv.finalProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.unibuc.egv.finalProject.services.GameService;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    //region Games page
    @RequestMapping("/store/games")
    public String gamesInit(){
        System.out.println("Games page accessed!");
        return "games";
    }
    //endregion

    //region Add Games page
    @RequestMapping("/admin/add_products/add_games")
    public String addGamesInit(){
        System.out.println("Add games page accessed!");
        return "add_games";
    }
    //endregion

    //region Edit Games page
    @RequestMapping("/admin/edit_products/edit_games")
    public String editGamesInit(){
        System.out.println("Edit games page accessed!");
        return "edit_games";
    }
    //endregion

    //region Delete Games page
    @RequestMapping("/admin/delete_products/delete_games")
    public String deleteGamesInit(){
        System.out.println("Delete games page accessed!");
        return "delete_games";
    }
    //endregion

}
