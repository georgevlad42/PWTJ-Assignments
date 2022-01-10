package ro.unibuc.egv.finalProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ro.unibuc.egv.finalProject.models.Game;
import ro.unibuc.egv.finalProject.services.GameService;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    //region Games page
    @RequestMapping("/store/games")
    @GetMapping("/store/games")
    public String gamesInit(Model model){
        System.out.println("Games page accessed!");
        model.addAttribute("gamesList", gameService.getGames());
        return "games";
    }

    @PostMapping("store/games")
    public String buyGame(@RequestParam("gameToBuyID") Long id, RedirectAttributes redirectAttributes){
        Game game = gameService.getGameByID(id);
        if (game.getProduct().getStatus().equals("Unavailable")){
            redirectAttributes.addFlashAttribute("errorBuyGame", game.getProduct().getName() + " is out of stock!");
            return "redirect:/store/games";
        }
        if (game.getProduct().getQuantity() >= 1) {
            game.getProduct().setQuantity(game.getProduct().getQuantity() - 1);
        }
        if (game.getProduct().getQuantity() == 0) {
            game.getProduct().setStatus("Unavailable");
        }
        gameService.updateGameQuantity(game);
        redirectAttributes.addFlashAttribute("successBuyGame", game.getProduct().getName() + " has been bought successfully!");
        redirectAttributes.addFlashAttribute("checkMail", "Check your email for more details!");
        return "redirect:/store/games";
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
