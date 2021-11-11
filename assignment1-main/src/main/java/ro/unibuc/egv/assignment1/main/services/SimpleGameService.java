package ro.unibuc.egv.assignment1.main.services;

import org.springframework.stereotype.Service;
import ro.unibuc.egv.assignment1.main.dtos.GameDto;
import ro.unibuc.egv.assignment1.main.model.Game;
import ro.unibuc.egv.assignment1.main.repositories.GameRepository;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Service
public class SimpleGameService implements GameService{

    private final GameRepository repo;

    public SimpleGameService(GameRepository repo) {
        this.repo = repo;
    }

    @Override
    public void addGame(GameDto gameDto) {
        if (gameDto.getPrice() <= 0){
            System.out.println("Invalid game price. Ignoring...");
            return;
        }
        repo.add(new Game(gameDto.getName(), gameDto.getPrice()));
    }

    @Override
    public Set<GameDto> getAllGames() {
        return repo.findAll().stream()
                .map(game -> new GameDto(game.getName(),game.getPrice()))
                .collect(collectingAndThen(toCollection(LinkedHashSet::new), Collections::unmodifiableSet));
    }
}
