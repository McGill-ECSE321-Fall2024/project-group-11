package ca.mcgill.ecse321.videogamessystem.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.model.Game.Category;
import ca.mcgill.ecse321.videogamessystem.model.Game.ConsoleType;
import ca.mcgill.ecse321.videogamessystem.repository.GameRepository;
import ca.mcgill.ecse321.videogamessystem.service.GameService;

class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateGame_Success() {
        Game mockGame = new Game("Adventure Game", 10, 50, "Mystic Quest", Category.Adventure, ConsoleType.PS4);
        when(gameRepository.save(any(Game.class))).thenReturn(mockGame);

        Game createdGame = gameService.createGame("Adventure Game", 10, 50, "Mystic Quest", Category.Adventure, ConsoleType.PS4);

        assertNotNull(createdGame);
        assertEquals("Mystic Quest", createdGame.getTitle());
        assertEquals(50, createdGame.getPrice());
        assertEquals(Category.Adventure, createdGame.getCategory());
    }
    // TODO : Fix
    // @Test
    // public void testCreateGame_InvalidPrice() {
    //     Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    //         gameService.createGame("Invalid Price Game", 10, -5, "Negative Price", Category.Action, ConsoleType.PC);
    //     });
    //     assertEquals("Price must be a positive value.", exception.getMessage());
    // }

    @Test
    public void testGetGameById_Found() {
        Game mockGame = new Game("Puzzle Game", 5, 30, "Puzzle Master", Category.Puzzle, ConsoleType.Switch);
        when(gameRepository.findGameById(1L)).thenReturn(mockGame);

        Game fetchedGame = gameService.getGameById(1L);

        assertNotNull(fetchedGame);
        assertEquals("Puzzle Master", fetchedGame.getTitle());
        assertEquals(ConsoleType.Switch, fetchedGame.getConsoleType());
    }

    @Test
    public void testGetGameById_NotFound() {
        when(gameRepository.findGameById(1L)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gameService.getGameById(1L);
        });

        assertEquals("Game not found.", exception.getMessage());
    }

    @Test
    public void testUpdateStockQuantity_Success() {
        Game mockGame = new Game("Strategy Game", 5, 60, "War Tactics", Category.Strategy, ConsoleType.PC);
        when(gameRepository.findGameById(1L)).thenReturn(mockGame);
        when(gameRepository.save(any(Game.class))).thenReturn(mockGame);

        Game updatedGame = gameService.updateStockQuantity(1L, 8);

        assertNotNull(updatedGame);
        assertEquals(8, updatedGame.getStockQuantity());
    }

    @Test
    public void testUpdateStockQuantity_Invalid() {
        Game mockGame = new Game("Strategy Game", 5, 60, "War Tactics", Category.Strategy, ConsoleType.PC);
        when(gameRepository.findGameById(1L)).thenReturn(mockGame);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gameService.updateStockQuantity(1L, -10);
        });
        assertEquals("Stock quantity cannot be negative.", exception.getMessage());
    }

    @Test
    public void testGetGamesByCategory() {
        Game game1 = new Game("Racing Game", 10, 40, "Fast Lane", Category.Sports, ConsoleType.XBOX);
        Game game2 = new Game("Basketball Game", 15, 35, "Hoops Hero", Category.Sports, ConsoleType.Switch);
        
        when(gameRepository.findGameByCategory(Category.Sports)).thenReturn(List.of(game1, game2));

        List<Game> games = gameService.getGamesByCategory(Category.Sports);

        assertNotNull(games);
        assertEquals(2, games.size());
        assertTrue(games.stream().allMatch(game -> game.getCategory() == Category.Sports));
    }

    @Test
    public void testGetGamesByNonexistentCategory() {
        when(gameRepository.findGameByCategory(Category.Strategy)).thenReturn(List.of());

        List<Game> games = gameService.getGamesByCategory(Category.Strategy);

        assertNotNull(games);
        assertEquals(0, games.size(), "Expected no games to be returned for non-existent category.");
    }

    @Test
    public void testUpdatePrice_Success() {
        Game mockGame = new Game("Action Game", 20, 45, "Battlefield", Category.Action, ConsoleType.PC);
        when(gameRepository.findGameById(1L)).thenReturn(mockGame);
        when(gameRepository.save(any(Game.class))).thenReturn(mockGame);

        Game updatedGame = gameService.updatePrice(1L, 55);

        assertNotNull(updatedGame);
        assertEquals(55, updatedGame.getPrice());
    }
    //TODO : fix
    // @Test
    // public void testUpdatePrice_Invalid() {
    //     Game mockGame = new Game("Action Game", 20, 45, "Battlefield", Category.Action, ConsoleType.PC);
    //     when(gameRepository.findGameById(1L)).thenReturn(mockGame);

    //     Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    //         gameService.updatePrice(1L, -20);
    //     });
    //     assertEquals("Price must be a positive value.", exception.getMessage());
    // }
 
    @Test
    public void testDeleteGame_Success() {
        Game mockGame = new Game("Party Game", 12, 20, "Party Stars", Category.Party, ConsoleType.Wii);
        when(gameRepository.findGameById(1L)).thenReturn(mockGame);
        doNothing().when(gameRepository).delete(mockGame);

        Game deletedGame = gameService.deleteGame(1L);

        assertNotNull(deletedGame);
        assertEquals("Party Stars", deletedGame.getTitle());
        verify(gameRepository, times(1)).delete(mockGame);
    }

    @Test
    public void testDeleteGame_NotFound() {
        when(gameRepository.findGameById(1L)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gameService.deleteGame(1L);
        });
        assertEquals("Game not found.", exception.getMessage());
    }

    @Test
    public void testCreateGame_WithLowStock() {
        Game mockGame = new Game("Rare Game", 1, 100, "Rare Quest", Category.Adventure, ConsoleType.PS4);
        when(gameRepository.save(any(Game.class))).thenReturn(mockGame);

        Game createdGame = gameService.createGame("Rare Game", 1, 100, "Rare Quest", Category.Adventure, ConsoleType.PS4);

        assertNotNull(createdGame);
        assertEquals(1, createdGame.getStockQuantity(), "Stock quantity should be set to 1 for limited games.");
    }

    @Test
    public void testCreateGame_InvalidStockQuantity() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gameService.createGame("Negative Stock Game", -3, 25, "Impossible Quest", Category.Adventure, ConsoleType.PC);
        });
        assertEquals("Stock quantity cannot be negative.", exception.getMessage());
    }

    @Test
    public void testGetAllGames() {
        Game game1 = new Game("Action Game", 15, 40, "Battle Quest", Category.Action, ConsoleType.PS4);
        Game game2 = new Game("Mystery Game", 8, 30, "Mystery Manor", Category.Adventure, ConsoleType.PC);
        
        when(gameRepository.findAll()).thenReturn(List.of(game1, game2));

        List<Game> games = gameService.getAllGames();

        assertNotNull(games);
        assertEquals(2, games.size());
    }
}
