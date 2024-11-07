package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.GameConsole;

public interface GameConsoleRepository extends CrudRepository<GameConsole, Integer>{
    public GameConsole findGameConsoleById(int gameConsoleId);
}