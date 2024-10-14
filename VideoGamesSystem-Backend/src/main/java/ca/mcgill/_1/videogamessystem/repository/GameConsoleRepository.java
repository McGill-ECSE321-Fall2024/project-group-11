package ca.mcgill._1.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill._1.videogamessystem.model.GameConsole;

public interface GameConsoleRepository extends CrudRepository<GameConsole, Integer>{
    public GameConsole findGameConsoleById(int id);
}
