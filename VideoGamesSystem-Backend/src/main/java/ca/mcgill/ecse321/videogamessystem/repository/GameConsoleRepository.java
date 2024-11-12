package ca.mcgill.ecse321.videogamessystem.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.videogamessystem.model.Console;
import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.model.GameConsole;
import ca.mcgill.ecse321.videogamessystem.model.GameConsoleId;

public interface GameConsoleRepository extends CrudRepository<GameConsole, GameConsoleId> {
    
    GameConsole findGameConsoleByGameConsoleID(GameConsoleId gameConsoleId);

    // Find all GameConsole entries by Game
    List<GameConsole> findGameConsoleByGame(Game game);

    // Find all GameConsole entries by Console
    List<GameConsole> findGameConsoleByConsole(Console console);

    // Find a specific GameConsole entry by Game and Console
    GameConsole findGameConsoleByGameAndConsole(Game game, Console console);

}
