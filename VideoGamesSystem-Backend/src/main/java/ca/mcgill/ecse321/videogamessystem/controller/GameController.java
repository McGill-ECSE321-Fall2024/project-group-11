package ca.mcgill.ecse321.videogamessystem.controller;

import ca.mcgill.ecse321.videogamessystem.dto.GameDto.GameRequestDto;
import ca.mcgill.ecse321.videogamessystem.dto.GameDto.GameResponseDto;
import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class GameController {

    @Autowired
    private GameService gameService;

    /**
     * Creates a new game with the specified details.
     *
     * @param gameRequestDto the data transfer object containing game details.
     * @return a GameResponseDto with the created game details.
     */
    @PostMapping("/games")
    public GameResponseDto createGame(@Valid @RequestBody GameRequestDto gameRequestDto) {
        Game game = gameService.createGame(
                gameRequestDto.getDescription(),
                gameRequestDto.getPrice(),
                gameRequestDto.getTitle(),
                gameRequestDto.getCategory(),
                gameRequestDto.getConsoleType()
        );
        return new GameResponseDto(game);
    }

    /**
     * Retrieves a game by its unique ID.
     *
     * @param id the ID of the game to retrieve.
     * @return a GameResponseDto with the details of the requested game.
     */
    @GetMapping("/games/{id}")
    public GameResponseDto getGameById(@PathVariable Long id) {
        Game game = gameService.getGameById(id);
        return new GameResponseDto(game);
    }

    /**
     * Retrieves all games.
     *
     * @return a list of GameResponseDto containing details of all games.
     */
    @GetMapping("/games")
    public List<GameResponseDto> getAllGames() {
        List<Game> games = gameService.getAllGames();
        return games.stream().map(GameResponseDto::new).collect(Collectors.toList());
    }

    /**
     * Deletes a game by its unique ID.
     *
     * @param id the ID of the game to delete.
     * @return a GameResponseDto containing details of the deleted game.
     */
    @DeleteMapping("/games/{id}")
    public GameResponseDto deleteGame(@PathVariable Long id) {
        Game deletedGame = gameService.deleteGame(id);
        return new GameResponseDto(deletedGame);
    }

    /**
     * Retrieves games based on their title.
     *
     * @param title the title to filter games by.
     * @return a list of GameResponseDto containing details of games with the specified title.
     */
    @GetMapping("/games/title/{title}")
    public List<GameResponseDto> getGamesByTitle(@PathVariable String title) {
        List<Game> games = gameService.getGamesByTitle(title);
        return games.stream().map(GameResponseDto::new).collect(Collectors.toList());
    }

    /**
     * Retrieves games based on a specified category.
     *
     * @param category the category to filter games by.
     * @return a list of GameResponseDto containing details of games within the specified category.
     */
    @GetMapping("/games/category/{category}")
    public List<GameResponseDto> getGamesByCategory(@PathVariable Game.Category category) {
        List<Game> games = gameService.getGamesByCategory(category);
        return games.stream().map(GameResponseDto::new).collect(Collectors.toList());
    }

    /**
     * Retrieves games based on a specified console type.
     *
     * @param consoleType the console type to filter games by.
     * @return a list of GameResponseDto containing details of games compatible with the specified console type.
     */
    @GetMapping("/games/console/{consoleType}")
    public List<GameResponseDto> getGamesByConsoleType(@PathVariable Game.ConsoleType consoleType) {
        List<Game> games = gameService.getGamesByConsoleType(consoleType);
        return games.stream().map(GameResponseDto::new).collect(Collectors.toList());
    }

    /**
     * Adds a game to a wishlist.
     *
     * @param gameId the ID of the game to add.
     * @param wishlistId the ID of the wishlist to add the game to.
     * @return a GameResponseDto with the updated game details after adding it to the wishlist.
     */
    @PutMapping("/games/{gameId}/wishlist/{wishlistId}")
    public GameResponseDto addGameToWishlist(@PathVariable Long gameId, @PathVariable Long wishlistId) {
        Game game = gameService.addGameToWishlist(gameId, wishlistId);
        return new GameResponseDto(game);
    }

    /**
     * Retrieves games within a specified price range.
     *
     * @param min the minimum price.
     * @param max the maximum price.
     * @return a list of GameResponseDto containing details of games within the specified price range.
     */
    @GetMapping("/games/price")
    public List<GameResponseDto> getGamesBetweenPrices(@RequestParam int min, @RequestParam int max) {
        List<Game> games = gameService.getGamesBetweenPrices(min, max);
        return games.stream().map(GameResponseDto::new).collect(Collectors.toList());
    }
}