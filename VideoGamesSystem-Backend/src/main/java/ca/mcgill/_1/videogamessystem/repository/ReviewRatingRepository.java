package ca.mcgill._1.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill._1.videogamessystem.model.ReviewRating;
import ca.mcgill._1.videogamessystem.model.ReviewRating.Status;

public interface ReviewRatingRepository extends CrudRepository<ReviewRating, Long>{
    public ReviewRating findReviewRatingByStatus(Status ratingStatus);
}
