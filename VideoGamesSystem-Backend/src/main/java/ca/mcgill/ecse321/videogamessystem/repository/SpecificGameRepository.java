
package ca.mcgill.ecse321.videogamessystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.SpecificGame;

public interface SpecificGameRepository extends CrudRepository<SpecificGame, Integer>{
   // find the game with its serial number
    public SpecificGame findSpecificGameBySerialNumber(int serialNumber);
// list all the gamne that are (non) available 
    public List<SpecificGame> findSpecificGameByavailability(Boolean availability);
}
