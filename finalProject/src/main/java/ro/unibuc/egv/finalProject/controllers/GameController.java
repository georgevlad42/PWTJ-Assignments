package ro.unibuc.egv.finalProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ro.unibuc.egv.finalProject.mail.MailSenderPSM;
import ro.unibuc.egv.finalProject.models.Game;
import ro.unibuc.egv.finalProject.models.Product;
import ro.unibuc.egv.finalProject.models.User;
import ro.unibuc.egv.finalProject.services.GameService;
import ro.unibuc.egv.finalProject.services.ProductService;

import javax.servlet.http.HttpSession;

@Controller
public class GameController {

    private final ProductService productService;
    private final GameService gameService;

    public GameController(ProductService productService, GameService gameService) {
        this.productService = productService;
        this.gameService = gameService;
    }

    //region Games page
    @RequestMapping(value = "/store/games", method = RequestMethod.GET)
    public String gamesInit(Model model){
        System.out.println("Games page accessed!");
        model.addAttribute("gamesList", gameService.getGames());
        return "games";
    }

    @RequestMapping(value = "/store/games", method = RequestMethod.POST)
    public String buyGame(@RequestParam("gameToBuyID") Long id, RedirectAttributes redirectAttributes, HttpSession httpSession){
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
        gameService.updateGameStatus(game);
        redirectAttributes.addFlashAttribute("successBuyGame", game.getProduct().getName() + " has been bought successfully!");
        redirectAttributes.addFlashAttribute("checkMail", "Check your email for more details!");
        MailSenderPSM mailSenderPSM = new MailSenderPSM((User) httpSession.getAttribute("currentUser"), game.getProduct(), "PSM - Game Purchase");
        mailSenderPSM.sendMessage();
        return "redirect:/store/games";
    }
    //endregion

    //region Add Games page
    @RequestMapping(value = "/admin/add_products/add_games", method = RequestMethod.GET)
    public String addGamesInit(Model model){
        System.out.println("Add games page accessed!");
        model.addAttribute("newGame", new Game());
        return "add_games";
    }

    @RequestMapping(value = "/admin/add_products/add_games", method = RequestMethod.POST)
    public String addNewGame(@ModelAttribute("newGame") Game newGame, Model model, RedirectAttributes redirectAttributes){
        if(productService.isProductNameNotUnique(newGame.getProduct().getName())){
            model.addAttribute("errorProductName", "Product name is already in use!");
            return "add_games";
        }
        gameService.addGame(newGame);
        redirectAttributes.addFlashAttribute("successAddGame", "The game has been added successfully!");
        return "redirect:/admin/add_products/add_games";
    }

    //endregion

    //region Edit Games page
    @RequestMapping(value = "/admin/edit_products/edit_games", method = RequestMethod.GET)
    public String editGamesInit(Model model){
        System.out.println("Edit games page accessed!");
        model.addAttribute("gamesList", gameService.getGames());
        return "edit_games";
    }

    @RequestMapping(value = "/admin/edit_products/edit_games", method = RequestMethod.POST)
    public String editGame(@RequestParam("gameToEditID") Long id, @RequestParam("gameToEditProductID") Long productID,
                           @RequestParam("gameProductNameToEdit") String name, @RequestParam("gameProductPriceToEdit") Double price,
                           @RequestParam("gameProductQtyToEdit") Integer quantity, @RequestParam("gameProductDescToEdit") String description,
                           @RequestParam("gamePlatformToEdit") String platform, @RequestParam("gameEditionToEdit") String edition,
                           @RequestParam("gameGenreToEdit") String genre, @RequestParam("gameModeToEdit") String gameMode,
                           @RequestParam("gamePublisherToEdit") String publisher, @RequestParam("gameDeveloperToEdit") String developer,
                           Model model, RedirectAttributes redirectAttributes){
        Game gameToEdit = new Game(id, new Product(productID, name, price, quantity, description, gameService.getGameByID(id).getProduct().getStatus()), platform, edition, genre, gameMode, publisher, developer);
        if (gameToEdit.equals(gameService.getGameByID(gameToEdit.getGameID()))) {
            model.addAttribute("errorEditGame", "There are no changes made to the selected game!");
            model.addAttribute("gamesList", gameService.getGames());
            return "edit_games";
        }
        if (productService.isProductNameNotUnique(gameToEdit.getProduct().getName()) && !gameToEdit.getProduct().getName().equals(gameService.getGameByID(id).getProduct().getName())) {
            model.addAttribute("errorEditProductName", "The product name " + gameToEdit.getProduct().getName() + " is already in use!");
            model.addAttribute("gamesList", gameService.getGames());
            return "edit_games";
        }
        gameService.editGame(gameToEdit);
        redirectAttributes.addFlashAttribute("successEditGame", "The game has been updated successfully!");
        return "redirect:/admin/edit_products/edit_games";
    }
    //endregion

    //region Delete Games page
    @RequestMapping(value = "/admin/delete_products/delete_games", method = RequestMethod.GET)
    public String deleteGamesInit(Model model){
        System.out.println("Delete games page accessed!");
        model.addAttribute("gamesList", gameService.getGames());
        return "delete_games";
    }

    @RequestMapping(value = "/admin/delete_products/delete_games", method = RequestMethod.POST)
    public String deleteGame(@RequestParam("gameToDeleteID") Long id, RedirectAttributes redirectAttributes){
        gameService.deleteGame(id);
        redirectAttributes.addFlashAttribute("successDeleteGame", "Game was deleted successfully");
        return "redirect:/admin/delete_products/delete_games";
    }
    //endregion

}
