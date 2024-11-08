package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.SpecificReviewRating;

public interface SpecificReviewRatingRepository extends CrudRepository<SpecificReviewRating, Long>{
    // find the specific review with id
    public SpecificReviewRating findReviewRatingById(Long id);
}
