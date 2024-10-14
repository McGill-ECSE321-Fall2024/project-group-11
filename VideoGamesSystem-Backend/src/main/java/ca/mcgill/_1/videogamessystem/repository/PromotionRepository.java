package ca.mcgill._1.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill._1.videogamessystem.model.Promotion;

public interface PromotionRepository extends CrudRepository<Promotion, Integer>{
    
}
