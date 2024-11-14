package ca.mcgill.ecse321.videogamessystem.controller;

import ca.mcgill.ecse321.videogamessystem.dto.GameDto.GameRequestDto;
import ca.mcgill.ecse321.videogamessystem.dto.GameDto.GameResponseDto;
import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    // Create a new game
    @PostMapping
    public GameResponseDto createGame(@RequestBody GameRequestDto gameRequestDto) {
        Game game = gameService.createGame(
                gameRequestDto.getDescription(),
                0,  // Stock quantity should be set to 0 initially or handled differently if needed
                gameRequestDto.getPrice(),
                gameRequestDto.getTitle(),
                gameRequestDto.getCategory(),
                gameRequestDto.getConsoleType()
        );
        return new GameResponseDto(game);
    }

    // Get a game by ID
    @GetMapping("/{id}")
    public GameResponseDto getGameById(@PathVariable Long id) {
        Game game = gameService.getGameById(id);
        return new GameResponseDto(game);
    }

    // Get all games
    @GetMapping
    public List<GameResponseDto> getAllGames() {
        List<Game> games = gameService.getAllGames();
        return games.stream().map(GameResponseDto::new).collect(Collectors.toList());
    }

    // Update game details
    @PutMapping("/{id}")
    public GameResponseDto updateGame(@PathVariable Long id, @RequestBody GameRequestDto gameRequestDto) {
        Game updatedGame = gameService.updateGame(
                id,
                gameRequestDto.getDescription(),
                0,  // Assuming stock quantity management is separate; adjust as needed
                gameRequestDto.getPrice(),
                gameRequestDto.getTitle(),
                gameRequestDto.getCategory()
        );
        return new GameResponseDto(updatedGame);
    }

    // Delete a game by ID
    @DeleteMapping("/{id}")
    public GameResponseDto deleteGame(@PathVariable Long id) {
        Game deletedGame = gameService.deleteGame(id);
        return new GameResponseDto(deletedGame);
    }

    // Additional custom endpoints

    // Get games by price
    @GetMapping("/price/{price}")
    public List<GameResponseDto> getGamesByPrice(@PathVariable int price) {
        List<Game> games = gameService.getGamesByPrice(price);
        return games.stream().map(GameResponseDto::new).collect(Collectors.toList());
    }

    // Get games by title
    @GetMapping("/title/{title}")
    public List<GameResponseDto> getGamesByTitle(@PathVariable String title) {
        List<Game> games = gameService.getGamesByTitle(title);
        return games.stream().map(GameResponseDto::new).collect(Collectors.toList());
    }

    // Get games by category
    @GetMapping("/category/{category}")
    public List<GameResponseDto> getGamesByCategory(@PathVariable Game.Category category) {
        List<Game> games = gameService.getGamesByCategory(category);
        return games.stream().map(GameResponseDto::new).collect(Collectors.toList());
    }

    // Get games by console type
    @GetMapping("/console/{consoleType}")
    public List<GameResponseDto> getGamesByConsoleType(@PathVariable Game.ConsoleType consoleType) {
        List<Game> games = gameService.getGamesByConsoleType(consoleType);
        return games.stream().map(GameResponseDto::new).collect(Collectors.toList());
    }

    // Add a game to a wishlist
    @PutMapping("/{gameId}/wishlist/{wishlistId}")
    public GameResponseDto addGameToWishlist(@PathVariable Long gameId, @PathVariable Long wishlistId) {
        Game game = gameService.addGameToWishlist(gameId, wishlistId);
        return new GameResponseDto(game);
    }

    // Get games within a price range
    @GetMapping("/price-range")
    public List<GameResponseDto> getGamesBetweenPrices(@RequestParam int min, @RequestParam int max) {
        List<Game> games = gameService.getGamesBetweenPrices(min, max);
        return games.stream().map(GameResponseDto::new).collect(Collectors.toList());
    }

    // Get games with a promotion above a certain percentage
    @GetMapping("/promotion/{min}")
    public List<GameResponseDto> getGamesAbovePromotion(@PathVariable int min) {
        List<Game> games = gameService.getGamesAbovePromotion(min);
        return games.stream().map(GameResponseDto::new).collect(Collectors.toList());
    }
}

