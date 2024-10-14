package ca.mcgill.ecse321.videogamessystem.repository;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Promotion;

public interface PromotionRepository extends CrudRepository<Promotion, Long>{
    public Promotion findPromotionById(Long id);
}
