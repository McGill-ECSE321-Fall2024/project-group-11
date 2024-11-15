package ca.mcgill.ecse321.videogamessystem.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.model.Game;
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


    @Test
    public void testGetReviewsByReviewDate_ReviewsExist() {
        // Arrange
        Date reviewDate = Date.valueOf(LocalDate.now());
        Review review1 = new Review(4, "Great!", reviewDate);
        Review review2 = new Review(5, "Amazing!", reviewDate);

        when(reviewRepository.findReviewByReviewDate(reviewDate)).thenReturn(List.of(review1, review2));

        // Act
        List<Review> reviews = reviewService.getReviewsByReviewDate(reviewDate);

        // Assert
        assertNotNull(reviews);
        assertEquals(2, reviews.size());
        assertTrue(reviews.stream().allMatch(r -> r.getReviewDate().equals(reviewDate)));
        verify(reviewRepository, times(1)).findReviewByReviewDate(reviewDate);
    }

    @Test
    public void testGetReviewsByGameRating_ReviewsExist() {
        // Arrange
        int gameRating = 5;
        Review review1 = new Review(5, "Amazing!", Date.valueOf(LocalDate.now()));
        Review review2 = new Review(5, "Outstanding!", Date.valueOf(LocalDate.now()));

        when(reviewRepository.findReviewByGameRating(gameRating)).thenReturn(List.of(review1, review2));

        // Act
        List<Review> reviews = reviewService.getReviewsByGameRating(gameRating);

        // Assert
        assertNotNull(reviews);
        assertEquals(2, reviews.size());
        assertTrue(reviews.stream().allMatch(r -> r.getGameRating() == gameRating));
        verify(reviewRepository, times(1)).findReviewByGameRating(gameRating);
    }

    @Test
    public void testGetReviewsByParentReview_Success() {
        // Arrange
        Long parentReviewId = 1L;
        Review parentReview = new Review(5, "Parent Review", Date.valueOf(LocalDate.now()));
        Review childReview1 = new Review(4, "Child Review 1", Date.valueOf(LocalDate.now()));
        Review childReview2 = new Review(3, "Child Review 2", Date.valueOf(LocalDate.now()));

        when(reviewRepository.findReviewById(parentReviewId)).thenReturn(parentReview);
        when(reviewRepository.findReviewByParentReview(parentReview)).thenReturn(List.of(childReview1, childReview2));

        // Act
        List<Review> childReviews = reviewService.getReviewsByParentReview(parentReviewId);

        // Assert
        assertNotNull(childReviews);
        assertEquals(2, childReviews.size());
        assertTrue(childReviews.contains(childReview1));
        assertTrue(childReviews.contains(childReview2));
        verify(reviewRepository, times(1)).findReviewById(parentReviewId);
        verify(reviewRepository, times(1)).findReviewByParentReview(parentReview);
    }

    @Test
    public void testGetReviewsByParentReview_ParentNotFound() {
        // Arrange
        Long parentReviewId = 999L;
        when(reviewRepository.findReviewById(parentReviewId)).thenReturn(null);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reviewService.getReviewsByParentReview(parentReviewId);
        });
        assertEquals("Parent review not found.", exception.getMessage());
        verify(reviewRepository, times(1)).findReviewById(parentReviewId);
        verify(reviewRepository, times(0)).findReviewByParentReview(any(Review.class));
    }
    @Test
    public void testUpdateReviewDate_ReviewNotFound() {
        // Arrange
        Long reviewId = 999L;
        Date newDate = Date.valueOf(LocalDate.now());
        when(reviewRepository.findReviewById(reviewId)).thenReturn(null);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reviewService.updateReviewDate(reviewId, newDate);
        });

        // Assert Exception Message
        assertEquals("Review not found.", exception.getMessage());
        verify(reviewRepository, times(1)).findReviewById(reviewId);
        verify(reviewRepository, times(0)).save(any(Review.class));
    }

    @Test
    public void testUpdateReviewDate_NullNewDate() {
        // Arrange
        Long reviewId = 1L;
        when(reviewRepository.findReviewById(reviewId)).thenReturn(review);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reviewService.updateReviewDate(reviewId, null);
        });

        // Assert Exception Message
        assertEquals("Review date cannot be in the future.", exception.getMessage());
        verify(reviewRepository, times(1)).findReviewById(reviewId);
        verify(reviewRepository, times(0)).save(any(Review.class));
    }
    @Test
    public void testUpdateReviewDate_NewDateInFuture() {
        // Arrange
        Long reviewId = 1L;
        Date futureDate = Date.valueOf(LocalDate.now().plusDays(1)); // A future date
        when(reviewRepository.findReviewById(reviewId)).thenReturn(review);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reviewService.updateReviewDate(reviewId, futureDate);
        });

        // Assert Exception Message
        assertEquals("Review date cannot be in the future.", exception.getMessage());
        verify(reviewRepository, times(1)).findReviewById(reviewId);
        verify(reviewRepository, times(0)).save(any(Review.class));
    }

    @Test
    public void testUpdateReviewDate_Success() {
        // Arrange
        Long reviewId = 1L;
        Date validDate = Date.valueOf(LocalDate.now().minusDays(1)); // A valid past date
        when(reviewRepository.findReviewById(reviewId)).thenReturn(review);
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        // Act
        Review updatedReview = reviewService.updateReviewDate(reviewId, validDate);

        // Assert
        assertNotNull(updatedReview);
        assertEquals(validDate, updatedReview.getReviewDate());
        verify(reviewRepository, times(1)).findReviewById(reviewId);
        verify(reviewRepository, times(1)).save(review);
    }

    @Test
    public void testGetAllReviews_WithMultipleReviews() {
        // Arrange
        Review review1 = new Review();
        Review review2 = new Review();
        when(reviewRepository.findAll()).thenReturn(List.of(review1, review2));

        // Act
        List<Review> result = reviewService.getAllReviews();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(review1));
        assertTrue(result.contains(review2));
    }

    @Test
    public void testGetAllReviews_WithNoReviews() {
        // Arrange
        when(reviewRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Review> result = reviewService.getAllReviews();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    @Test
    public void testGetReviewsByGame_NullGame() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reviewService.getReviewsByGame(null);
        });
        assertEquals("game cannot be null", exception.getMessage());
    }
    @Test
    public void testGetReviewsByGame_WithReviews() {
        // Arrange
        Game game = new Game();
        Review review1 = new Review(5, "Great game!", Date.valueOf(LocalDate.now()));
        Review review2 = new Review(4, "Good game.", Date.valueOf(LocalDate.now()));
        when(reviewRepository.findReviewByGame(game)).thenReturn(List.of(review1, review2));

        // Act
        List<Review> reviews = reviewService.getReviewsByGame(game);

        // Assert
        assertNotNull(reviews);
        assertEquals(2, reviews.size());
        assertTrue(reviews.contains(review1));
        assertTrue(reviews.contains(review2));
    }
}
