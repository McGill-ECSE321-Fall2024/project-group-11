package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Review;
import java.util.List;
import java.util.Date;


public interface ReviewRepository extends CrudRepository<Review, Long>{
    public Review findReviewById(Long id);

    List<Review> findReviewByReviewDate(Date reviewDate);

    List<Review> findReviewByGameRating(int gameRating);
}

