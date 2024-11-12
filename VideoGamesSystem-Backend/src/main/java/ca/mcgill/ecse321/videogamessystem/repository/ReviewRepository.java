package ca.mcgill.ecse321.videogamessystem.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.model.Review;


public interface ReviewRepository extends CrudRepository<Review, Long>{

    Review findReviewById(Long id);

    List<Review> findReviewByReviewDate(Date reviewDate);

    List<Review> findReviewByGameRating(int gameRating);

    List<Review> findReviewByParentReview(Review parentReview);

    List<Review> findReviewByCustomer(Customer customer);
}

