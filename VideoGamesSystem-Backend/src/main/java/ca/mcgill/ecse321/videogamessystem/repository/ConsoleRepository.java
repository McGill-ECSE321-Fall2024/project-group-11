package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Console;

public interface ConsoleRepository extends CrudRepository<Console, Long>{
    public Console findConsoleById(Long id);
}