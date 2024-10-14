package ca.mcgill._1.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill._1.videogamessystem.model.Game;

public interface GameRepository extends CrudRepository<Game, Long>{
    public Game findGameById(Long id);
}
