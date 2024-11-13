package ca.mcgill.ecse321.videogamessystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.ArrayList;

import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.model.Game.Category;
import ca.mcgill.ecse321.videogamessystem.model.Game.ConsoleType;
import ca.mcgill.ecse321.videogamessystem.model.Promotion;
import ca.mcgill.ecse321.videogamessystem.model.SpecificGame;
import ca.mcgill.ecse321.videogamessystem.model.SpecificOrder;
import ca.mcgill.ecse321.videogamessystem.model.Wishlist;
import ca.mcgill.ecse321.videogamessystem.repository.GameRepository;
import ca.mcgill.ecse321.videogamessystem.repository.PromotionRepository;

@Service
public class GameService {

    private GameRepository gameRepository;
    private PromotionRepository promotionRepository;

    @Autowired
    public GameService(GameRepository gameRepository, PromotionRepository promotionRepository) {
        this.gameRepository = gameRepository;
        this.promotionRepository = promotionRepository;
    }

    @Transactional
    public Game createGame(String description, int stockQuantity, int price, String title, Category category, ConsoleType consoleType) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null.");
        }
        if (consoleType == null) {
            throw new IllegalArgumentException("consoleType cannot be null.");
        }

        Game game = new Game(description, stockQuantity, price, title, category, consoleType);
        return gameRepository.save(game);
    }

    @Transactional
    public Game getGameById(Long id) {
        Game game = gameRepository.findGameById(id);
        if (game == null) {
            throw new IllegalArgumentException("Game not found.");
        }
        return game;
    }

    @Transactional
    public List<Game> getGamesByStockQuantity(int stockQuantity) {
        return gameRepository.findGameByStockQuantity(stockQuantity);
    }

    @Transactional
    public List<Game> getGamesByPrice(int price) {
        return gameRepository.findGameByprice(price);
    }

    @Transactional
    public List<Game> getGamesByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        return gameRepository.findGameByTitle(title);
    }

    @Transactional
    public List<Game> getGamesByCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null.");
        }
        return gameRepository.findGameByCategory(category);
    }

    @Transactional
    public List<Game> getGamesByConsoleType(ConsoleType consoleType) {
        if (consoleType == null) {
            throw new IllegalArgumentException("consoleType cannot be null.");
        }
        return gameRepository.findGameByConsoleType(consoleType);
    }

    @Transactional
    public List<Game> getGamesByPromotion(Promotion promotion) {
        if(promotion == null){
            new IllegalArgumentException("promotion is null");
        }
        return gameRepository.findGameByPromotion(promotion);
    }

    @Transactional
    public Game updateCategory(Long id, Category category) {
        Game game = gameRepository.findGameById(id);
        if (game == null) {
            throw new IllegalArgumentException("invalid id to update category");
        }
        game.setCategory(category);
        return gameRepository.save(game);

    }

    @Transactional
    public Game updateConsoleType(Long id, ConsoleType consoleType) {
        Game game = gameRepository.findGameById(id);
        if (game == null) {
            throw new IllegalArgumentException("invalid id to update consoleType");
        }
        game.setConsoleType(consoleType);
        return gameRepository.save(game);
    }

    @Transactional
    public Game updateGame(long id, String description, int stockQuantity, int price, String title, Category category){
        Game game = gameRepository.findGameById(id);
        if (game == null) {
            throw new IllegalArgumentException("invalid id to update description");
        }
        game.setDescription(description);
        game.setStockQuantity(stockQuantity);
        game.setPrice(price);
        game.setDescription(title);
        game.setCategory(category);
        return gameRepository.save(game);
    }



    @Transactional
    public Game updateStockQuantity(Long id, int newStockQuantity) {
        Game game = gameRepository.findGameById(id);
        if (game == null) {
            throw new IllegalArgumentException("Game not found.");
        }
        if (newStockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative.");
        }

        game.setStockQuantity(newStockQuantity);
        return gameRepository.save(game);
    }

    @Transactional
    public Game updatePrice(Long id, int newPrice) {
        Game game = gameRepository.findGameById(id);
        if (game == null) {
            throw new IllegalArgumentException("Game not found.");
        }
        if (newPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

        game.setPrice(newPrice);
        return gameRepository.save(game);
    }

    @Transactional
    public Game updateDescription(Long id, String newDescription) {
        Game game = gameRepository.findGameById(id);
        if (game == null) {
            throw new IllegalArgumentException("Game not found.");
        }
        if (newDescription == null || newDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }

        game.setDescription(newDescription);
        return gameRepository.save(game);
    }

    @Transactional
    public Game updateTitle(Long id, String newTitle) {
        Game game = gameRepository.findGameById(id);
        if (game == null) {
            throw new IllegalArgumentException("Game not found.");
        }
        if (newTitle == null || newTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }

        game.setTitle(newTitle);
        return gameRepository.save(game);
    }

    @Transactional
    public Game deleteGame(Long id) {
        Game game = gameRepository.findGameById(id);
        if (game == null) {
            throw new IllegalArgumentException("Game not found.");
        }

        gameRepository.delete(game);
        return game;
    }
    

    @Transactional
    public List<Game> getAllGames() {
        return toList(gameRepository.findAll());
    }

    /**
     * Converts an {@code Iterable} to a {@code List}.
     * @param iterable the {@code Iterable} to convert
     * @param <T>      the type of elements in the iterable
     * @return a {@code List} containing the elements of the {@code Iterable}
     */
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

    public List<Game> getGameByWishlist(Wishlist wishlist){
        if (wishlist == null){
            throw new IllegalArgumentException("wishlist is null");
        }
        return gameRepository.findGameByWishlist(wishlist);
    }

    //is game available , fsire par rapport a quantity
    // does game have promoition
    // add game to promo 
    // add game to wishlist
    // set consoletype to game
    // get price with promo 
    // add promotion to game
     
    //get all game names by order
    //public List<SpecificGame> getAllGamesFromOrder(int specificGameID, SpecificOrder order) {

    //}
}
