package ca.mcgill.ecse321.videogamessystem.repository;
import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.videogamessystem.model.Promotion;

@Repository
public interface PromotionRepository extends CrudRepository<Promotion, Long>{
    // find the promotion with id 
    Promotion findPromotionById(Long id);

    // List<Promotion> findPromotionByPercentage(int percentage);

    // // lsit all the promotion with same start date
    // List<Promotion> findPromotionByStartDate(Date startDate);

    // // list all the promotion with same end date
    // List<Promotion> findPromotionByEndDate(Date endDate);
}