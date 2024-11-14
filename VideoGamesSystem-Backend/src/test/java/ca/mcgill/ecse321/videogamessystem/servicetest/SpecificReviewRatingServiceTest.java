package ca.mcgill.ecse321.videogamessystem.servicetest;

import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.model.Review;
import ca.mcgill.ecse321.videogamessystem.model.SpecificReviewRating;
import ca.mcgill.ecse321.videogamessystem.model.SpecificReviewRating.ReviewRating;
import ca.mcgill.ecse321.videogamessystem.repository.CustomerRepository;
import ca.mcgill.ecse321.videogamessystem.repository.ReviewRepository;
import ca.mcgill.ecse321.videogamessystem.repository.SpecificReviewRatingRepository;
import ca.mcgill.ecse321.videogamessystem.service.SpecificReviewRatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SpecificReviewRatingServiceTest {

    @Mock
    private SpecificReviewRatingRepository specificReviewRatingRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private SpecificReviewRatingService specificReviewRatingService;

    private Review review;
    private Customer customer;
    private SpecificReviewRating specificReviewRating;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize common objects
        review = mock(Review.class);
        when(review.getId()).thenReturn(1L);

        customer = mock(Customer.class);
        when(customer.getId()).thenReturn(1L);

        specificReviewRating = new SpecificReviewRating(ReviewRating.Like);
        specificReviewRating.setReview(review);
        specificReviewRating.setCustomer(customer);
    }

    @Test
    public void testCreateSpecificReviewRating_Success() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(specificReviewRatingRepository.save(any(SpecificReviewRating.class))).thenReturn(specificReviewRating);

        SpecificReviewRating createdRating = specificReviewRatingService.createSpecificReviewRating(ReviewRating.Like, 1L, 1L);

        assertNotNull(createdRating);
        assertEquals(ReviewRating.Like, createdRating.getReviewRating());
        assertEquals(review, createdRating.getReview());
        assertEquals(customer, createdRating.getCustomer());
    }

    @Test
    public void testCreateSpecificReviewRating_ReviewNotFound() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            specificReviewRatingService.createSpecificReviewRating(ReviewRating.Like, 1L, 1L)
        );

        assertEquals("Review not found.", exception.getMessage());
    }

    @Test
    public void testCreateSpecificReviewRating_CustomerNotFound() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            specificReviewRatingService.createSpecificReviewRating(ReviewRating.Like, 1L, 1L)
        );

        assertEquals("Customer not found.", exception.getMessage());
    }

    @Test
    public void testGetSpecificReviewRatingById_Found() {
        when(specificReviewRatingRepository.findSpecificReviewRatingById(1L)).thenReturn(specificReviewRating);

        SpecificReviewRating foundRating = specificReviewRatingService.getSpecificReviewRatingById(1L);

        assertNotNull(foundRating);
        assertEquals(ReviewRating.Like, foundRating.getReviewRating());
    }

    @Test
    public void testGetSpecificReviewRatingById_NotFound() {
        when(specificReviewRatingRepository.findSpecificReviewRatingById(1L)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            specificReviewRatingService.getSpecificReviewRatingById(1L)
        );

        assertEquals("Specific review rating not found.", exception.getMessage());
    }

    @Test
    public void testGetSpecificReviewRatingsByReview_Success() {
        List<SpecificReviewRating> ratings = new ArrayList<>();
        ratings.add(specificReviewRating);
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(specificReviewRatingRepository.findSpecificReviewRatingByReview(review)).thenReturn(ratings);

        List<SpecificReviewRating> result = specificReviewRatingService.getSpecificReviewRatingsByReview(1L);

        assertEquals(1, result.size());
        assertEquals(ReviewRating.Like, result.get(0).getReviewRating());
    }

    @Test
    public void testUpdateReviewRating_Success() {
        when(specificReviewRatingRepository.findSpecificReviewRatingById(1L)).thenReturn(specificReviewRating);
        when(specificReviewRatingRepository.save(any(SpecificReviewRating.class))).thenReturn(specificReviewRating);

        SpecificReviewRating updatedRating = specificReviewRatingService.updateReviewRating(1L, ReviewRating.Dislike);

        assertNotNull(updatedRating);
        assertEquals(ReviewRating.Dislike, updatedRating.getReviewRating());
    }

    @Test
    public void testUpdateReviewRating_NotFound() {
        when(specificReviewRatingRepository.findSpecificReviewRatingById(1L)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            specificReviewRatingService.updateReviewRating(1L, ReviewRating.Dislike)
        );

        assertEquals("Specific review rating not found.", exception.getMessage());
    }

    @Test
    public void testDeleteSpecificReviewRating_Success() {
        when(specificReviewRatingRepository.findSpecificReviewRatingById(1L)).thenReturn(specificReviewRating);
        doNothing().when(specificReviewRatingRepository).delete(specificReviewRating);

        SpecificReviewRating deletedRating = specificReviewRatingService.deleteSpecificReviewRating(1L);

        assertNotNull(deletedRating);
        assertEquals(specificReviewRating, deletedRating);
        verify(specificReviewRatingRepository, times(1)).delete(specificReviewRating);
    }

    @Test
    public void testDeleteSpecificReviewRating_NotFound() {
        when(specificReviewRatingRepository.findSpecificReviewRatingById(1L)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            specificReviewRatingService.deleteSpecificReviewRating(1L)
        );

        assertEquals("Specific review rating not found.", exception.getMessage());
    }

    @Test
    public void testGetAllSpecificReviewRatings() {
        List<SpecificReviewRating> ratings = new ArrayList<>();
        ratings.add(specificReviewRating);
        when(specificReviewRatingRepository.findAll()).thenReturn(ratings);

        List<SpecificReviewRating> result = specificReviewRatingService.getAllSpecificReviewRatings();

        assertEquals(1, result.size());
    }
}
