package ca.mcgill.ecse321.videogamessystem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.model.Game.Category;
import ca.mcgill.ecse321.videogamessystem.model.Game.ConsoleType;
import ca.mcgill.ecse321.videogamessystem.model.Promotion;
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
    public void testUpdatePrice_Success() {
        Game mockGame = new Game("Action Game", 20, 45, "Battlefield", Category.Action, ConsoleType.PC);
        when(gameRepository.findGameById(1L)).thenReturn(mockGame);
        when(gameRepository.save(any(Game.class))).thenReturn(mockGame);

        Game updatedGame = gameService.updatePrice(1L, 55);

        assertNotNull(updatedGame);
        assertEquals(55, updatedGame.getPrice());
    }

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
}
