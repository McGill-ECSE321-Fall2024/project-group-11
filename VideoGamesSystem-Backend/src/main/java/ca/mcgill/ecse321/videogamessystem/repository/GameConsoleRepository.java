package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.GameConsole;
import ca.mcgill.ecse321.videogamessystem.model.GameConsoleId;

public interface GameConsoleRepository extends CrudRepository<GameConsole, GameConsoleId> {
    GameConsole findGameConsoleByGameConsoleID(GameConsoleId gameConsoleId);
}