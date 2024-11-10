package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.SpecificReviewRating;
import ca.mcgill.ecse321.videogamessystem.model.ReviewRating;
import java.util.List;

public interface SpecificReviewRatingRepository extends CrudRepository<SpecificReviewRating, Long>{
    public SpecificReviewRating findSpecificReviewRatingById(Long id);

    List<SpecificReviewRating> findSpecificReviewRatingByReviewRating(ReviewRating reviewRating);
}
