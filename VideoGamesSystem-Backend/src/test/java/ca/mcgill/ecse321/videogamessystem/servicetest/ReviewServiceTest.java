package ca.mcgill.ecse321.videogamessystem.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.model.Review;
import ca.mcgill.ecse321.videogamessystem.repository.CustomerRepository;
import ca.mcgill.ecse321.videogamessystem.repository.ReviewRepository;
import ca.mcgill.ecse321.videogamessystem.service.ReviewService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private ReviewService reviewService;

    private Review review;
    private Customer customer;

    @BeforeEach
    public void setup() {
        customer = new Customer();
        review = new Review(5, "Amazing game!", Date.valueOf(LocalDate.now()));
        review.setCustomer(customer);
    }

    @Test
    public void testCreateReview_Success() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        Review createdReview = reviewService.createReview(5, "Amazing game!", 1L, null);

        assertNotNull(createdReview);
        assertEquals(5, createdReview.getGameRating());
        assertEquals("Amazing game!", createdReview.getReviewContent());
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    public void testCreateReview_InvalidRating() {
        assertThrows(IllegalArgumentException.class, () -> reviewService.createReview(6, "Amazing game!", 1L, null));
    }

    @Test
    public void testGetReviewById_Found() {
        when(reviewRepository.findReviewById(anyLong())).thenReturn(review);

        Review foundReview = reviewService.getReviewById(1L);

        assertNotNull(foundReview);
        assertEquals(5, foundReview.getGameRating());
        assertEquals("Amazing game!", foundReview.getReviewContent());
        verify(reviewRepository, times(1)).findReviewById(anyLong());
    }

    @Test
    public void testUpdateReviewContent_Success() {
        when(reviewRepository.findReviewById(anyLong())).thenReturn(review);
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        Review updatedReview = reviewService.updateReviewContent(1L, "Updated content");

        assertNotNull(updatedReview);
        assertEquals("Updated content", updatedReview.getReviewContent());
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    public void testDeleteReview_Success() {
        when(reviewRepository.findReviewById(anyLong())).thenReturn(review);
        doNothing().when(reviewRepository).delete(any(Review.class));

        Review deletedReview = reviewService.deleteReview(1L);

        assertNotNull(deletedReview);
        verify(reviewRepository, times(1)).delete(review);
    }

    @Test
    public void testGetReviewsByCustomer() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(reviewRepository.findReviewByCustomer(any(Customer.class))).thenReturn(Collections.singletonList(review));

        List<Review> reviews = reviewService.getReviewsByCustomer(1L);

        assertNotNull(reviews);
        assertEquals(1, reviews.size());
        assertEquals("Amazing game!", reviews.get(0).getReviewContent());
        verify(reviewRepository, times(1)).findReviewByCustomer(any(Customer.class));
    }

    @Test
    public void testUpdateGameRating_Success() {
        when(reviewRepository.findReviewById(anyLong())).thenReturn(review);
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        Review updatedReview = reviewService.updateGameRating(1L, 4);

        assertNotNull(updatedReview);
        assertEquals(4, updatedReview.getGameRating());
        verify(reviewRepository, times(1)).save(any(Review.class));
    }
}
