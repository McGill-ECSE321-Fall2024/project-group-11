package ca.mcgill.ecse321.videogamessystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Game;

public interface GameRepository extends CrudRepository<Game, Long>{
    // find the game with its id 
    public Game findGameById(Long id);
    // find the game using its title
    public Game findGameBytitle(String title);
    // list the games of the same category
    public List<Game> findGameBycategory(String category);
    
}