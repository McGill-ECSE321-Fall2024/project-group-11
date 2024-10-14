package ca.mcgill._1.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill._1.videogamessystem.model.Review;

public interface ReviewRepository extends CrudRepository<Review, Integer>{
    public Review findReview(int rating, String content);
}
