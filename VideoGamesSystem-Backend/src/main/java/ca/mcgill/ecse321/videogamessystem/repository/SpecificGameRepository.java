
package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.SpecificGame;

import java.util.List;

public interface SpecificGameRepository extends CrudRepository<SpecificGame, Integer>{
    public SpecificGame findSpecificGameBySerialNumber(int serialNumber);

    List<SpecificGame> findSpecificGameByAvailability(boolean availability);
}
