package ca.mcgill.ecse321.videogamessystem.service;
//import static org.mockito.Mockito.description;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.model.Game.Category;
import ca.mcgill.ecse321.videogamessystem.model.Promotion;
import ca.mcgill.ecse321.videogamessystem.repository.GameRepository;
import jakarta.transaction.Transactional;
@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional
    public Game createGame(String description, int stockQantity, int price, String title, ca.mcgill.ecse321.videogamessystem.model.Game.Category category) {

        if (price < 0 || title.length() < 1 ) {
            throw new IllegalArgumentException("Invalid Input brotha");
        }

        Game game = new Game(); // change in model the mapping to not have too many arguments
        game.setDescription(description);
        game.setStockQuantity(stockQantity);
        game.setPrice(price);
        game.setTitle(title);
        game.setCategory(category);

        return gameRepository.save(game);
    }

    /**
     * @param id
     * @return a game using the id
     */
    @Transactional
    public Game getGameById(Long id){
        Game game = gameRepository.findGameById(id);
        if (game == null) {
            throw new IllegalArgumentException("There is no game with ID " + id + ".");
        }
        return game;
    }

    public List<Game> getGameByStockQuantity(int stockQantity){
        return gameRepository.findGameByStockQuantity(stockQantity);
    }

    public List<Game> getGameByPrice(int price){
        return gameRepository.findGameByprice(price);
    }

    public List<Game> getGameByTitle(String title){
        return gameRepository.findGameByTitle(title);
    }


    public List<Game> getGameBycategory(ca.mcgill.ecse321.videogamessystem.model.Game.Category category){
        return gameRepository.findGameByCategory(category);
    }
    

    public List<Game> getGameByConsole(Console console){
        return gameRepository.findGameByConsole(console);
    }
    

    public List<Game> getGameByPromotion(Promotion promotion){
        return gameRepository.findGameByPromotion(promotion);
    }

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
        return game;

    }

    public Game updateGameDescription(Long id, String description){
        Game game = gameRepository.findGameById(id);
        if(game == null){
            throw new IllegalArgumentException("invalid id to update description");
        }
        game.setDescription(description);
        return game;

    }

    public Game updateGamestockQuantity(Long id, int stockQuantity){
        Game game = gameRepository.findGameById(id);
        if (game == null) {
            throw new IllegalArgumentException("invalid id to update description");
            
        }
        game.setStockQuantity(stockQuantity);
        return game;

    }

    public Game updateGameprice(Long id, int price) {
        Game game = gameRepository.findGameById(id);
        if (game == null) {
            throw new IllegalArgumentException("invalid id to update description");

        }

        if (price < 0 ) {
            throw new IllegalArgumentException("price needs to be positive");

        }

        game.setPrice(price);
        return game;

    }

    public Game updateGameTitle(Long id, String title) {
        Game game = gameRepository.findGameById(id);
        if (game == null) {
            throw new IllegalArgumentException("invalid id to update description");
        }
        game.setDescription(title);
        return game;

    }

    public Game updateCategory(Long id, Category category) {
        Game game = gameRepository.findGameById(id);
        if (game == null) {
            throw new IllegalArgumentException("invalid id to update description");
        }
        game.setCategory(category);
        return game;

    }

    


    public Game deleteGame(Long id){
        Game exsistingGame = gameRepository.findGameById(id);
/*
 * if (game == null) {
 * throw new IllegalArgumentException("invalid id to update description");
 * }
 * 
 */ 
 //List<SpecificGame> specificgame = Game.findAllspecificgame();

        return exsistingGame;
    }

}
