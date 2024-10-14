package ca.mcgill._1.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill._1.videogamessystem.model.ReviewRating;

public interface ReviewRatingRepository extends CrudRepository<ReviewRating, Long>{
    public ReviewRating findReviewRatingById(Long id);
}
