package ca.mcgill.ecse321.videogamessystem.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Review;

public interface ReviewRepository extends CrudRepository<Review, Long>{
    // find the review usinf=g the id
    public Review findReviewById(Long id);
// list of review with the same publication date
    public List<Review> findReviewByreviewDate(Date reviewDate);
}

