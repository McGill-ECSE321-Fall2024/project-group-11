package ca.mcgill.ecse321.videogamessystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Console;
import ca.mcgill.ecse321.videogamessystem.model.Game;

public interface ConsoleRepository extends CrudRepository<Console, Long>{
    
    Console findConsoleById(Long id);

    Console findConsoleByConsoleType(String consoleType);

    List<Console> findConsoleByGame(Game game);
}


