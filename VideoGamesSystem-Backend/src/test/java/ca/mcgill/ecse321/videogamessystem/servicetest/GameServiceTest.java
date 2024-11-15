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
        Game mockGame = new Game("Adventure Game", 50, "Mystic Quest", Category.Adventure, ConsoleType.PS4);
        when(gameRepository.save(any(Game.class))).thenReturn(mockGame);

        Game createdGame = gameService.createGame("Adventure Game", 50, "Mystic Quest", Category.Adventure, ConsoleType.PS4);

        assertNotNull(createdGame);
        assertEquals("Mystic Quest", createdGame.getTitle());
        assertEquals(50, createdGame.getPrice());
        assertEquals(Category.Adventure, createdGame.getCategory());
    }

    @Test
    public void testCreateGame_InvalidPrice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gameService.createGame("Invalid Price Game", -5, "Negative Price", Category.Action, ConsoleType.PC);
        });
        assertEquals("Price cannot be negative.", exception.getMessage());
    }

    @Test
    public void testGetGameById_Found() {
        Game mockGame = new Game("Puzzle Game", 30, "Puzzle Master", Category.Puzzle, ConsoleType.Switch);
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
    public void testGetGamesByCategory() {
        Game game1 = new Game("Racing Game", 40, "Fast Lane", Category.Sports, ConsoleType.XBOX);
        Game game2 = new Game("Basketball Game", 35, "Hoops Hero", Category.Sports, ConsoleType.Switch);
        
        when(gameRepository.findGameByCategory(Category.Sports)).thenReturn(List.of(game1, game2));

        List<Game> games = gameService.getGamesByCategory(Category.Sports);

        assertNotNull(games);
        assertEquals(2, games.size());
        assertTrue(games.stream().allMatch(game -> game.getCategory() == Category.Sports));
    }

    @Test
    public void testUpdatePrice_Success() {
        Game mockGame = new Game("Action Game", 45, "Battlefield", Category.Action, ConsoleType.PC);
        when(gameRepository.findGameById(1L)).thenReturn(mockGame);
        when(gameRepository.save(any(Game.class))).thenReturn(mockGame);

        Game updatedGame = gameService.updatePrice(1L, 55);

        assertNotNull(updatedGame);
        assertEquals(55, updatedGame.getPrice());
    }

    @Test
    public void testDeleteGame_Success() {
        Game mockGame = new Game("Party Game", 20, "Party Stars", Category.Party, ConsoleType.Wii);
        when(gameRepository.findGameById(1L)).thenReturn(mockGame);
        doNothing().when(gameRepository).delete(mockGame);

        Game deletedGame = gameService.deleteGame(1L);

        assertNotNull(deletedGame);
        assertEquals("Party Stars", deletedGame.getTitle());
        verify(gameRepository, times(1)).delete(mockGame);
    }

    @Test
    public void testUpdateTitle_Success() {
        Game mockGame = new Game("Old Title", 50, "Some Description", Category.Adventure, ConsoleType.PS4);
        when(gameRepository.findGameById(1L)).thenReturn(mockGame);
        when(gameRepository.save(any(Game.class))).thenReturn(mockGame);

        Game updatedGame = gameService.updateTitle(1L, "New Title");

        assertNotNull(updatedGame);
        assertEquals("New Title", updatedGame.getTitle());
    }

    @Test
    public void testUpdateDescription_Success() {
        Game mockGame = new Game("Game Title", 50, "Old Description", Category.Adventure, ConsoleType.PS4);
        when(gameRepository.findGameById(1L)).thenReturn(mockGame);
        when(gameRepository.save(any(Game.class))).thenReturn(mockGame);

        Game updatedGame = gameService.updateDescription(1L, "New Description");

        assertNotNull(updatedGame);
        assertEquals("New Description", updatedGame.getDescription());
    }
    //TODO
    // @Test
    // public void testGetGamesByTitle_Success() {
    //     Game game1 = new Game("Adventure Quest", 40, "Description1", Category.Adventure, ConsoleType.PS4);
    //     Game game2 = new Game("Adventure World", 50, "Description2", Category.Adventure, ConsoleType.Switch);
    //     when(gameRepository.findGameByTitle("Adventure")).thenReturn(List.of(game1, game2));

    //     List<Game> games = gameService.getGamesByTitle("Adventure");

    //     assertNotNull(games);
    //     assertEquals(2, games.size());
    //     assertTrue(games.stream().allMatch(game -> game.getTitle().contains("Adventure")));
    // }

    @Test
    public void testGetGamesByConsoleType() {
        Game game1 = new Game("Game 1", 50, "Console Game", Category.Adventure, ConsoleType.PS4);
        Game game2 = new Game("Game 2", 45, "Console Game 2", Category.Strategy, ConsoleType.PS4);
        when(gameRepository.findGameByConsoleType(ConsoleType.PS4)).thenReturn(List.of(game1, game2));

        List<Game> games = gameService.getGamesByConsoleType(ConsoleType.PS4);

        assertNotNull(games);
        assertEquals(2, games.size());
        assertTrue(games.stream().allMatch(game -> game.getConsoleType() == ConsoleType.PS4));
    }
    //TODO
    // @Test
    // public void testAddGameToWishlist() {
    //     Game game = new Game("Game 1", 50, "Console Game", Category.Adventure, ConsoleType.PS4);
    //     when(gameRepository.findGameById(1L)).thenReturn(game);

    //     Game addedGame = gameService.addGameToWishlist(1L, 2L); // Assuming `wishlistId` is needed

    //     assertNotNull(addedGame);
    //     assertEquals("Game 1", addedGame.getTitle());
    // }
    //TODO
    // @Test
    // public void testGetAvailableGames() {
    //     Game game1 = new Game("Available Game 1", 50, "Description 1", Category.Sports, ConsoleType.Switch);
    //     Game game2 = new Game("Available Game 2", 60, "Description 2", Category.Adventure, ConsoleType.XBOX);
    //     when(gameRepository.findAll()).thenReturn(List.of(game1, game2));

    //     List<Game> games = gameService.getAvailableGames();

    //     assertNotNull(games);
    //     assertEquals(2, games.size());
    // }

    @Test
    public void testUpdateCategory_Success() {
        Game mockGame = new Game("Action Game", 45, "Exciting action game", Category.Adventure, ConsoleType.PC);
        when(gameRepository.findGameById(1L)).thenReturn(mockGame);
        when(gameRepository.save(any(Game.class))).thenReturn(mockGame);

        Game updatedGame = gameService.updateCategory(1L, Category.Sports);

        assertNotNull(updatedGame);
        assertEquals(Category.Sports, updatedGame.getCategory());
    }

    @Test
    public void testUpdateConsoleType_Success() {
        Game mockGame = new Game("Adventure Game", 50, "A fun game", Category.Adventure, ConsoleType.PC);
        when(gameRepository.findGameById(1L)).thenReturn(mockGame);
        when(gameRepository.save(any(Game.class))).thenReturn(mockGame);

        Game updatedGame = gameService.updateConsoleType(1L, ConsoleType.PS4);

        assertNotNull(updatedGame);
        assertEquals(ConsoleType.PS4, updatedGame.getConsoleType());
    }

    // @Test
    // public void testGetGameByTitle_Success() {
    //     Game game1 = new Game("Epic Adventure", 50, "An epic adventure game", Category.Adventure, ConsoleType.PS4);
    //     when(gameRepository.findGameByTitle("Epic Adventure")).thenReturn(List.of(game1));

    //     List<Game> games = gameService.getGamesByTitle("Epic Adventure");

    //     assertNotNull(games);
    //     assertEquals(1, games.size());
    //     assertEquals("Epic Adventure", games.get(0).getTitle());
    // }

    @Test
    public void testGetAllGames() {
        Game game1 = new Game("Action Game", 40, "Battle Quest", Category.Action, ConsoleType.PS4);
        Game game2 = new Game("Mystery Game", 30, "Mystery Manor", Category.Adventure, ConsoleType.PC);
        
        when(gameRepository.findAll()).thenReturn(List.of(game1, game2));

        List<Game> games = gameService.getAllGames();

        assertNotNull(games);
        assertEquals(2, games.size());
    }

    // @Test
    // public void testUpdateGame_FullUpdate() {
    //     Game mockGame = new Game("Old Game", 40, "Old Description", Category.Puzzle, ConsoleType.XBOX);
    //     when(gameRepository.findGameById(1L)).thenReturn(mockGame);
    //     when(gameRepository.save(any(Game.class))).thenReturn(mockGame);

    //     Game updatedGame = gameService.updateGame(1L, "New Description", 55, "New Game", Category.Sports);

    //     assertNotNull(updatedGame);
    //     assertEquals("New Game", updatedGame.getTitle());
    //     assertEquals("New Description", updatedGame.getDescription());
    //     assertEquals(55, updatedGame.getPrice());
    //     assertEquals(Category.Sports, updatedGame.getCategory());
    // }

    // @Test
    // public void testUpdateGame_InvalidPrice() {
    //     Game mockGame = new Game("Old Game", 40, "Old Description", Category.Puzzle, ConsoleType.XBOX);
    //     when(gameRepository.findGameById(1L)).thenReturn(mockGame);

    //     Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    //         gameService.updateGame(1L, "New Description", -10, "New Game", Category.Sports);
    //     });

    //     assertEquals("Price cannot be negative.", exception.getMessage());
    // }

    

    @Test
    public void testDeleteGame_NotFound() {
        when(gameRepository.findGameById(1L)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gameService.deleteGame(1L);
        });
        assertEquals("Game not found.", exception.getMessage());
    }
    
    
    
}
