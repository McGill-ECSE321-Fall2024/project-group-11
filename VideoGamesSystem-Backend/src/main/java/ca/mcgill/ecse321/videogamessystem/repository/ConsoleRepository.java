package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import ca.mcgill.ecse321.videogamessystem.model.Console;

public interface ConsoleRepository extends CrudRepository<Console, Long>{
    public Console findConsoleById(Long id);

    List<Console> findConsoleByConsoleType(String consoleType);
}


