package ca.mcgill._1.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill._1.videogamessystem.model.SpecificGame;

public interface SpecificGameRepository extends CrudRepository<SpecificGame, Integer>{
    public SpecificGame findSpecificGameBySerialNumber(int serialNumber);
}
