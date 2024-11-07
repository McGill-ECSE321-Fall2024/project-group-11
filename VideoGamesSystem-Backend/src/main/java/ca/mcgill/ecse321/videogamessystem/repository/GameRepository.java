package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Game;

public interface GameRepository extends CrudRepository<Game, Long>{
    public Game findGameById(Long id);
}