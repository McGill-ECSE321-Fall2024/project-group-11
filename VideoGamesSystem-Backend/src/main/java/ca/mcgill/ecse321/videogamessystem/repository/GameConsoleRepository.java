package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.GameConsole;
import ca.mcgill.ecse321.videogamessystem.model.GameConsoleId;

public interface GameConsoleRepository extends CrudRepository<GameConsole, GameConsoleId> {
    // find the game consol with its id 
    GameConsole findGameConsoleByGameConsoleID(GameConsoleId gameConsoleId);
}