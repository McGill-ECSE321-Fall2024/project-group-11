package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Integer>{
    public Owner findOwnerById(int idNum);
}
