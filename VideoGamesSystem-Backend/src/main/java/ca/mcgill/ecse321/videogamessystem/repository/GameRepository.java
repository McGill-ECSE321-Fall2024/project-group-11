package ca.mcgill.ecse321.videogamessystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.model.Category;

public interface GameRepository extends CrudRepository<Game, Long>{
    public Game findGameById(Long id);

    Game findGameByTitle(String title);

    List<Game> findGameByCategory(Category category);
}