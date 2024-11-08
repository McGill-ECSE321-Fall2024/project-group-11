package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Review;

public interface ReviewRepository extends CrudRepository<Review, Long>{
    public Review findReviewById(Long id);
}

