package ro.unibuc.egv.assignment1.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ro.unibuc.egv.assignment1.main.dtos.GameDto;
import ro.unibuc.egv.assignment1.main.services.GameService;

@Controller
public class GameController {

    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getGameList(Model model){
        model.addAttribute("gameList", service.getAllGames());
        return "index";
    }

    @PostMapping("/")
    public String addGame(@RequestParam("name") String name, @RequestParam("price") int price, Model model){
        service.addGame(new GameDto(name, price));
        model.addAttribute("gameList", service.getAllGames());
        return "index";
    }

}
