package ca.mcgill.ecse321.videogamessystem.repository;

import java.io.Console;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.model.Game.Category;
import ca.mcgill.ecse321.videogamessystem.model.Promotion;

public interface GameRepository extends CrudRepository<Game, Long>{

    Game findGameById(Long id);

    List<Game> findGameByStockQantity(int stockQuantity);

    List<Game> findGameByprice(int price);

    List<Game> findGameByTitle(String title);

    List<Game> findGameByCategory(Category category);

    List<Game> findGameByConsole(Console console);

    List<Game> findByPromotion(Promotion promotion);
}