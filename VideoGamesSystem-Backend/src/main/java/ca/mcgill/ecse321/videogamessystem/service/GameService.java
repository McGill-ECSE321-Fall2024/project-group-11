package ca.mcgill.ecse321.videogamessystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.model.Category;
import ca.mcgill.ecse321.videogamessystem.repository.GameRepository;
import jakarta.transaction.Transactional;
@Service
public class GameService {

    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository){
        this.gameRepository= gameRepository;
    }

    public Game getGameById(Long gameId) {
        Game game = gameRepository.findGameById(gameId);
        if (game == null) {
            throw new IllegalArgumentException("There is no game with ID " + gameId + ".");
        }
        return game;
    }

    @Transactional
    public Game createGame(String description, int stockQantity, int price, String title, Game.Category category) {

        if (price < 0 || title.length() < 1 ) {
            throw new IllegalArgumentException("Invalid Input brotha");
        }

        
        Game gameToCreate = new Game(); // change in model the mapping to not have too many arguments

        gameToCreate.setDescription(description);
        gameToCreate.setStockQuantity(stockQantity);
        gameToCreate.setPrice(price);
        gameToCreate.setTitle(title);
        gameToCreate.setCategory(category);
        return gameRepository.save(gameToCreate);
    }



    /**
     * @param id
     * @return
     */

    //public Game getGameBytitle(){}


    //public List<Game> getGameBycategory(Category category){}
    
    
}
