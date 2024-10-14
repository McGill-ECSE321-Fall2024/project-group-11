package ca.mcgill._1.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill._1.videogamessystem.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Integer>{
    public Owner findOwnerById(int idNum);
}
