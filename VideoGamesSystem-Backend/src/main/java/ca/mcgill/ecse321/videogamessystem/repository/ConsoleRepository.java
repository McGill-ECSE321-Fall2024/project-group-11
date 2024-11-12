package ca.mcgill.ecse321.videogamessystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Console;
import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.model.Console.ConsoleType;

public interface ConsoleRepository extends CrudRepository<Console, Long>{
    
    Console findConsoleById(Long id);

    List<Console> findConsoleByConsoleType(ConsoleType consoleType);

}


