package ca.mcgill.ecse321.videogamessystem.repository;
import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;


import ca.mcgill.ecse321.videogamessystem.model.Promotion;

public interface PromotionRepository extends CrudRepository<Promotion, Long>{
    // find the promotion with id 
    public Promotion findPromotionById(Long id);
// lsit all the promotion with same start date
    public List<Promotion> findPromotionBystartDate(Date startdate);
// list all the promotion with same end date
    public List<Promotion> findPromotionByendDate(Date endDate);
    
}