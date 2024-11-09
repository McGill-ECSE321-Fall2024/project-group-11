package ca.mcgill.ecse321.videogamessystem.service;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.repository.GameRepository;
import jakarta.transaction.Transactional;
@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game getGameById(Long gameId) {
        Game game = gameRepository.findGameById(gameId);
        if (game == null) {
            throw new IllegalArgumentException("There is no game with ID " + pid + ".");
        }
        return game;
    }

    @Transactional
    public Game createGame(String description, int stockQantity, int price, String title, String category) {

        if (price < 0 || title.length() < 1 ) {
            throw new IllegalArgumentException("Invalid Input brotha");
        }

        
        Game gameToCreate = new Game(description, stockQantity, price, title, category); // change in model the mapping to not have too many arguments
        return gameRepository.save(gameToCreate);
    }



    /**
     * @param id
     * @return
     */
    public Game getGameById(Long id){
        Game game = this.gameRepository.findGameById(id);

    }

    public Game getGameBytitle(){}


    public List<Game> getGameBycategory(String category);{}

    public List<Game> getGameBycategory(String category);{}
    
}
