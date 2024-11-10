package ca.mcgill.ecse321.videogamessystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Console;

public interface ConsoleRepository extends CrudRepository<Console, Long>{
    public Console findConsoleById(Long id);

        // list all the consol of the same consol type
    public List<Console> findConsoleByconsolType(String consoleType);
}