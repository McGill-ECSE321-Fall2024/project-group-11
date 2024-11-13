package ca.mcgill.ecse321.videogamessystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.model.Review;
import ca.mcgill.ecse321.videogamessystem.repository.ReviewRepository;
import ca.mcgill.ecse321.videogamessystem.repository.CustomerRepository;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, CustomerRepository customerRepository) {
        this.reviewRepository = reviewRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Review createReview(int gameRating, String reviewContent, Long customerId, Long parentReviewId) {
        if (gameRating < 1 || gameRating > 5) {
            throw new IllegalArgumentException("Game rating must be between 1 and 5.");
        }
        if (reviewContent == null || reviewContent.trim().isEmpty()) {
            throw new IllegalArgumentException("Review content cannot be empty.");
        }

        Date reviewDate = Date.valueOf(LocalDate.now());

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> 
            new IllegalArgumentException("Customer not found."));
        
        Review parentReview = null;
        if (parentReviewId != null) {
            parentReview = reviewRepository.findReviewById(parentReviewId);
            if (parentReview == null) {
                throw new IllegalArgumentException("Parent review not found.");
            }
        }

        Review review = new Review(gameRating, reviewContent, reviewDate);
        review.setCustomer(customer);
        review.setParentReview(parentReview);

        return reviewRepository.save(review);
    }

    @Transactional
    public Review getReviewById(Long id) {
        Review review = reviewRepository.findReviewById(id);
        if (review == null) {
            throw new IllegalArgumentException("Review not found.");
        }
        return review;
    }

    @Transactional
    public List<Review> getReviewsByReviewDate(Date reviewDate) {
        return reviewRepository.findReviewByReviewDate(reviewDate);
    }

    @Transactional
    public List<Review> getReviewsByGameRating(int gameRating) {
        return reviewRepository.findReviewByGameRating(gameRating);
    }


    @Transactional
    public List<Review> getReviewsByParentReview(Long parentReviewId) {
        Review parentReview = reviewRepository.findReviewById(parentReviewId);
        if (parentReview == null) {
            throw new IllegalArgumentException("Parent review not found.");
        }
        return reviewRepository.findReviewByParentReview(parentReview);
    }

    @Transactional
    public List<Review> getReviewsByCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> 
            new IllegalArgumentException("Customer not found."));
        return reviewRepository.findReviewByCustomer(customer);
    }

    @Transactional
    public Review updateReviewContent(Long id, String newContent) {
        Review review = reviewRepository.findReviewById(id);
        if (review == null) {
            throw new IllegalArgumentException("Review not found.");
        }
        if (newContent == null || newContent.trim().isEmpty()) {
            throw new IllegalArgumentException("Review content cannot be empty.");
        }

        review.setReviewContent(newContent);
        return reviewRepository.save(review);
    }

    @Transactional
    public Review updateReviewDate(Long id, Date newDate) {
        Review review = reviewRepository.findReviewById(id);
        if (review == null) {
            throw new IllegalArgumentException("Review not found.");
        }
        if (newDate == null || newDate.after(Date.valueOf(LocalDate.now()))) {
            throw new IllegalArgumentException("Review date cannot be in the future.");
        }

        review.setReviewDate(newDate);
        return reviewRepository.save(review);
    }

    @Transactional
    public Review updateGameRating(Long id, int newRating) {
        Review review = reviewRepository.findReviewById(id);
        if (review == null) {
            throw new IllegalArgumentException("Review not found.");
        }
        if (newRating < 1 || newRating > 5) {
            throw new IllegalArgumentException("Game rating must be between 1 and 5.");
        }

        review.setGameRating(newRating);
        return reviewRepository.save(review);
    }

    @Transactional
    public Review deleteReview(Long id) {
        Review review = reviewRepository.findReviewById(id);
        if (review == null) {
            throw new IllegalArgumentException("Review not found.");
        }

        reviewRepository.delete(review);
        return review;
    }

    @Transactional
    public List<Review> getAllReviews() {
        return toList(reviewRepository.findAll());
    }

    /**
     * Converts an {@code Iterable} to a {@code List}.
     * @param iterable the {@code Iterable} to convert
     * @param <T>      the type of elements in the iterable
     * @return a {@code List} containing the elements of the {@code Iterable}
     */
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

    public List<Review> getReviewsByGame(Game game){
        if (game == null){
            throw new IllegalArgumentException("game cannot be null");
        }
        return this.reviewRepository.findReviewByGame(game);
    }
}

