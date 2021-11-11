package ro.unibuc.egv.assignment1.main.services;

import ro.unibuc.egv.assignment1.main.dtos.GameDto;

import java.util.Set;

public interface GameService {

    void addGame(GameDto gameDto);

    Set<GameDto> getAllGames();


}
