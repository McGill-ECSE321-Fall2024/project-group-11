package ca.mcgill.ecse321.videogamessystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.ArrayList;

import ca.mcgill.ecse321.videogamessystem.model.Review;
import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.model.SpecificReviewRating;
import ca.mcgill.ecse321.videogamessystem.model.SpecificReviewRating.ReviewRating;
import ca.mcgill.ecse321.videogamessystem.repository.SpecificReviewRatingRepository;
import ca.mcgill.ecse321.videogamessystem.repository.ReviewRepository;
import ca.mcgill.ecse321.videogamessystem.repository.CustomerRepository;

@Service
public class SpecificReviewRatingService {

    private SpecificReviewRatingRepository specificReviewRatingRepository;
    private ReviewRepository reviewRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public SpecificReviewRatingService(SpecificReviewRatingRepository specificReviewRatingRepository,
                                       ReviewRepository reviewRepository,
                                       CustomerRepository customerRepository) {
        this.specificReviewRatingRepository = specificReviewRatingRepository;
        this.reviewRepository = reviewRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public SpecificReviewRating createSpecificReviewRating(ReviewRating reviewRating, Long reviewId, Long customerId) {
        if (reviewRating == null) {
            throw new IllegalArgumentException("Review rating cannot be null.");
        }

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> 
            new IllegalArgumentException("Review not found."));
        
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> 
            new IllegalArgumentException("Customer not found."));

        SpecificReviewRating specificReviewRating = new SpecificReviewRating(reviewRating);
        specificReviewRating.setReview(review);
        specificReviewRating.setCustomer(customer);

        return specificReviewRatingRepository.save(specificReviewRating);
    }

    @Transactional
    public SpecificReviewRating getSpecificReviewRatingById(Long id) {
        SpecificReviewRating specificReviewRating = specificReviewRatingRepository.findSpecificReviewRatingById(id);
        if (specificReviewRating == null) {
            throw new IllegalArgumentException("Specific review rating not found.");
        }
        return specificReviewRating;
    }

    @Transactional
    public List<SpecificReviewRating> getSpecificReviewRatingsByReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> 
            new IllegalArgumentException("Review not found."));
        return specificReviewRatingRepository.findSpecificReviewRatingByReview(review);
    }

    @Transactional
    public SpecificReviewRating updateReviewRating(Long id, ReviewRating newReviewRating) {
        SpecificReviewRating specificReviewRating = specificReviewRatingRepository.findSpecificReviewRatingById(id);
        if (specificReviewRating == null) {
            throw new IllegalArgumentException("Specific review rating not found.");
        }
        if (newReviewRating == null) {
            throw new IllegalArgumentException("New review rating cannot be null.");
        }

        specificReviewRating.setReviewRating(newReviewRating);
        return specificReviewRatingRepository.save(specificReviewRating);
    }

    @Transactional
    public SpecificReviewRating deleteSpecificReviewRating(Long id) {
        SpecificReviewRating specificReviewRating = specificReviewRatingRepository.findSpecificReviewRatingById(id);
        if (specificReviewRating == null) {
            throw new IllegalArgumentException("Specific review rating not found.");
        }

        specificReviewRatingRepository.delete(specificReviewRating);
        return specificReviewRating;
    }

    @Transactional
    public List<SpecificReviewRating> getAllSpecificReviewRatings() {
        return toList(specificReviewRatingRepository.findAll());
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
}

