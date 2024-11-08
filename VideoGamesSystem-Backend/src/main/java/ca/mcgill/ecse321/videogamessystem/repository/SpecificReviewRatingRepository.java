package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.SpecificReviewRating;

public interface SpecificReviewRatingRepository extends CrudRepository<SpecificReviewRating, Long>{
    public SpecificReviewRating findReviewById(Long id);
}
