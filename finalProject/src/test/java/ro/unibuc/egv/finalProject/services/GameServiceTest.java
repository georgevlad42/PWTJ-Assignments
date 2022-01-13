package ro.unibuc.egv.finalProject.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import ro.unibuc.egv.finalProject.models.Game;
import ro.unibuc.egv.finalProject.models.Product;
import ro.unibuc.egv.finalProject.repositories.GameRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    @Test
    @DisplayName("A game was found by its ID")
    void testFindGameByID(){
        //Arrange
        Game game = new Game(new Product("PS Game", 100.00, 100, "Game Description", null), "PlayStation", "Standard", "Any", "Single-player", "Test Inc.", "Test Games");
        Game dbGame = new Game(1L, new Product("PS Game", 100.00, 100, "Game Description", null), "PlayStation", "Standard", "Any", "Single-player", "Test Inc.", "Test Games");
        when(gameRepository.findGameByGameID(1L)).thenReturn(dbGame);

        //Act
        gameService.addGame(game);
        Game result = gameRepository.findGameByGameID(dbGame.getGameID());

        //Assert
        assertNotNull(result);
        assertEquals(dbGame, result);
    }

    @Test
    @DisplayName("All games were found")
    void testFindAllGames(){
        //Arrange
        Game game1 = new Game(1L, new Product("PS Game 1", 100.00, 100, "Game 1 Description", null), "PlayStation", "Standard", "Any", "Single-player", "Test Inc.", "Test Games");
        Game game2 = new Game(2L, new Product("PS Game 2", 200.00, 200, "Game 2 Description", null), "PlayStation", "Standard", "Any", "Single-player", "Test Inc.", "Test Games");
        Game game3 = new Game(3L, new Product("PS Game 3", 300.00, 300, "Game 3 Description", null), "PlayStation", "Standard", "Any", "Single-player", "Test Inc.", "Test Games");
        Game dbGame1 = new Game(1L, new Product("PS Game 1", 100.00, 100, "Game 1 Description", "Available"), "PlayStation", "Standard", "Any", "Single-player", "Test Inc.", "Test Games");
        Game dbGame2 = new Game(2L, new Product("PS Game 2", 200.00, 200, "Game 2 Description", "Available"), "PlayStation", "Standard", "Any", "Single-player", "Test Inc.", "Test Games");
        Game dbGame3 = new Game(3L, new Product("PS Game 3", 300.00, 300, "Game 3 Description", "Available"), "PlayStation", "Standard", "Any", "Single-player", "Test Inc.", "Test Games");
        List<Game> gameList = new ArrayList<>();
        gameList.add(dbGame1);
        gameList.add(dbGame2);
        gameList.add(dbGame3);
        when(gameRepository.save(game1)).thenReturn(dbGame1);
        when(gameRepository.save(game2)).thenReturn(dbGame2);
        when(gameRepository.save(game3)).thenReturn(dbGame3);
        when(gameRepository.findAll()).thenReturn(gameList);

        //Act
        gameService.addGame(game1);
        gameService.addGame(game2);
        gameService.addGame(game3);
        List<Game> result = gameRepository.findAll();

        //Assert
        assertNotNull(result);
        assertEquals(gameList, result);
    }

    @Test
    @DisplayName("No games were found")
    void testFindNoGames(){
        //Arrange
        List<Game> gameList = new ArrayList<>();
        when(gameRepository.findAll()).thenReturn(gameList);

        //Act
        List<Game> result = gameRepository.findAll();

        //Assert
        assertEquals(gameList, result);
    }

    @Test
    @DisplayName("Last game was bought successfully")
    void testBuyLastGame(){
        //Arrange
        Game beforeBuyGame = new Game(1L,new Product("PS Game", 100.00, 1, "Game Description", "Available"), "PlayStation", "Standard", "Any", "Single-player", "Test Inc.", "Test Games");
        Game duringBuyGame = new Game(1L,new Product("PS Game", 100.00, 0, "Game Description", "Available"), "PlayStation", "Standard", "Any", "Single-player", "Test Inc.", "Test Games");
        Game afterBuyGame = new Game(1L,new Product("PS Game", 100.00, 0, "Game Description", "Unavailable"), "PlayStation", "Standard", "Any", "Single-player", "Test Inc.", "Test Games");
        when(gameRepository.save(duringBuyGame)).thenReturn(afterBuyGame);
        when(gameRepository.findGameByGameID(1L)).thenReturn(afterBuyGame);

        //Act
        gameService.updateGameStatus(duringBuyGame);
        Game result = gameRepository.findGameByGameID(afterBuyGame.getGameID());

        //Assert
        assertNotNull(result);
        assertNotEquals(beforeBuyGame.getProduct().getQuantity(), result.getProduct().getQuantity());
        assertNotEquals(beforeBuyGame.getProduct().getStatus(), result.getProduct().getStatus());
        assertEquals(duringBuyGame.getProduct().getQuantity(), result.getProduct().getQuantity());
        assertNotEquals(duringBuyGame.getProduct().getStatus(), result.getProduct().getStatus());
        assertEquals(afterBuyGame.getProduct().getQuantity(), result.getProduct().getQuantity());
        assertEquals(afterBuyGame.getProduct().getStatus(), result.getProduct().getStatus());
    }

    @Test
    @DisplayName("Game was created successfully")
    void testCreateGame(){
        //Arrange
        Game game = new Game(new Product("PS Game", 100.00, 100, "Game Description", null), "PlayStation", "Standard", "Any", "Single-player", "Test Inc.", "Test Games");
        Game savedGame = new Game(1L, new Product("PS Game", 100.00, 100, "Game Description", "Available"), "PlayStation", "Standard", "Any", "Single-player", "Test Inc.", "Test Games");
        when(gameRepository.save(game)).thenReturn(savedGame);
        when(gameRepository.findGameByGameID(1L)).thenReturn(savedGame);

        //Act
        gameService.addGame(game);
        Game result = gameRepository.findGameByGameID(savedGame.getGameID());

        //Assert
        assertNotNull(result);
        assertEquals(savedGame, result);
    }

    @Test
    @DisplayName("Game was updated successfully")
    void testUpdateGame(){
        //Arrange
        Game game = new Game(new Product("PS Game", 100.00, 100, "Game Description", null), "PlayStation", "Standard", "Any", "Single-player", "Test Inc.", "Test Games");
        Game updatedGame = new Game(1L, new Product("PS New Game", 200.00, 200, "New Game Description", "Available"),  "PlayStation 2", "Limited", "All", "Multi-player", "Test SRL", "Test Video-Games");
        when(gameRepository.findGameByGameID(1L)).thenReturn(updatedGame);

        //Act
        gameService.addGame(game);
        gameService.editGame(updatedGame);
        Game result = gameRepository.findGameByGameID(updatedGame.getGameID());

        //Assert
        assertNotNull(result);
        assertEquals(updatedGame, result);
    }

    @Test
    @DisplayName("Game was deleted successfully")
    void testDeleteGame(){
        //Arrange
        Game game = new Game(1L, new Product("PS Game", 100.00, 100, "Game Description", null), "PlayStation", "Standard", "Any", "Single-player", "Test Inc.", "Test Games");
        when(gameRepository.findGameByGameID(1L)).thenReturn(null);

        //Act
        gameService.deleteGame(game.getGameID());
        Game result = gameRepository.findGameByGameID(game.getGameID());

        //Assert
        assertNull(result);
    }

}
