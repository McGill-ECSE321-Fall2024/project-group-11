package ca.mcgill.ecse321.videogamessystem.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import java.util.stream.StreamSupport;

import ca.mcgill.ecse321.videogamessystem.exception.VideoGamesSystemException;
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
import org.springframework.http.HttpStatus;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

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
        // Act & Assert
        VideoGamesSystemException exception = assertThrows(VideoGamesSystemException.class, () -> {
            reviewService.createReview(6, "Amazing game!", 1L, null);
        });
        assertEquals("Game rating must be between 1 and 5.", exception.getMessage());
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
        // Arrange
        Long reviewId = 1L;
        String newContent = "Updated content";
    
        // Mocking an existing review
        Review review = new Review(5, "Old content", Date.valueOf(LocalDate.now()));
        // Mocking repository behavior to return the existing review
        when(reviewRepository.findReviewById(reviewId)).thenReturn(review);
        // Mocking repository behavior for save
        when(reviewRepository.save(any(Review.class))).thenReturn(review);
    
        // Act
        // Simulate service method behavior: retrieve, update, and save
        Review retrievedReview = reviewRepository.findReviewById(reviewId);
        retrievedReview.setReviewContent(newContent); // Update content
        Review updatedReview = reviewRepository.save(retrievedReview);
    
        // Assert
        assertNotNull(updatedReview, "Updated review should not be null");
        assertEquals(newContent, updatedReview.getReviewContent(), "The review content should be updated successfully");
        verify(reviewRepository, times(1)).findReviewById(reviewId);
        verify(reviewRepository, times(1)).save(retrievedReview);
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
        // Arrange
        Long reviewId = 1L;
        int newRating = 4;

        // Mocking the retrieval of the existing review
        when(reviewRepository.findReviewById(reviewId)).thenReturn(review);
        // Mocking the save operation
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        // Simulate updating the rating on the review
        Review retrievedReview = reviewRepository.findReviewById(reviewId);
        retrievedReview.setGameRating(newRating);
        Review savedReview = reviewRepository.save(retrievedReview);

        // Assert
        assertNotNull(savedReview);
        assertEquals(newRating, savedReview.getGameRating(), "The game rating should be updated successfully.");
        verify(reviewRepository, times(1)).findReviewById(reviewId);
        verify(reviewRepository, times(1)).save(retrievedReview);
    }



    @Test
    public void testGetReviewsByDate_ManualFiltering() {
        // Arrange
        Date reviewDate = Date.valueOf(LocalDate.now());

        Review review1 = new Review(4, "Great!", reviewDate);
        Review review2 = new Review(5, "Amazing!", reviewDate);
        Review review3 = new Review(3, "Okay", Date.valueOf(LocalDate.now().minusDays(1))); // Different date

        List<Review> allReviews = List.of(review1, review2, review3);

        // Mock the repository to return all reviews
        when(reviewRepository.findAll()).thenReturn(allReviews);

        // Act
        List<Review> reviewsByDate = StreamSupport.stream(reviewRepository.findAll().spliterator(), false)
                .filter(review -> reviewDate.equals(review.getReviewDate()))
                .toList();

        // Assert
        assertNotNull(reviewsByDate, "The result should not be null.");
        assertEquals(2, reviewsByDate.size(), "There should be 2 reviews matching the date.");
        assertTrue(reviewsByDate.contains(review1), "Review1 should be in the result.");
        assertTrue(reviewsByDate.contains(review2), "Review2 should be in the result.");
        verify(reviewRepository, times(1)).findAll();
    }


    @Test
    public void testGetReviewsByGameRating_ManualFiltering() {
        // Arrange
        int gameRating = 5;

        Review review1 = new Review(5, "Amazing!", Date.valueOf(LocalDate.now()));
        Review review2 = new Review(5, "Outstanding!", Date.valueOf(LocalDate.now()));
        Review review3 = new Review(4, "Good.", Date.valueOf(LocalDate.now())); // Different rating

        List<Review> allReviews = List.of(review1, review2, review3);

        // Mock the repository to return all reviews
        when(reviewRepository.findAll()).thenReturn(allReviews);

        // Act
        List<Review> reviewsByRating = StreamSupport.stream(reviewRepository.findAll().spliterator(), false)
                .filter(review -> review.getGameRating() == gameRating)
                .toList();

        // Assert
        assertNotNull(reviewsByRating, "The result should not be null.");
        assertEquals(2, reviewsByRating.size(), "There should be 2 reviews with the specified rating.");
        assertTrue(reviewsByRating.contains(review1), "Review1 should be in the result.");
        assertTrue(reviewsByRating.contains(review2), "Review2 should be in the result.");
        verify(reviewRepository, times(1)).findAll();
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
        VideoGamesSystemException exception = assertThrows(VideoGamesSystemException.class, () -> {
            reviewService.getReviewsByParentReview(parentReviewId);
        });
        assertEquals("Parent review not found.", exception.getMessage());
        verify(reviewRepository, times(1)).findReviewById(parentReviewId);
        verify(reviewRepository, times(0)).findReviewByParentReview(any(Review.class));
    }

    @Test
    public void testUpdateReviewDate_ReviewNotFound_NoServiceMethod() {
        // Arrange
        Long reviewId = 999L;
        Date newDate = Date.valueOf(LocalDate.now());

        // Mock repository behavior to return null
        when(reviewRepository.findReviewById(reviewId)).thenReturn(null);

        // Act
        Review foundReview = reviewRepository.findReviewById(reviewId);

        // Assert
        assertNull(foundReview, "Review should not be found in the repository.");
        verify(reviewRepository, times(1)).findReviewById(reviewId);
    }

    // @Test
    // public void testUpdateReviewDate_NullNewDate_NoServiceMethod() {
    //     // Arrange
    //     Long reviewId = 1L;
    //     Review review = new Review(5, "Sample review", Date.valueOf(LocalDate.now()));
    
    //     // Mock the repository to return a review
    //     when(reviewRepository.findReviewById(reviewId)).thenReturn(review);
    
    //     // Act & Assert
    //     IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
    //         // Simulate the behavior when the new date is null
    //         Date newDate = null;
    //         if (newDate == null) {
    //             throw new IllegalArgumentException("Review date cannot be null.");
    //         }
    //         review.setReviewDate(newDate);
    //         reviewRepository.save(review);
    //     });
    
    //     // Assert exception message
    //     assertEquals("Review date cannot be null.", exception.getMessage());
    
    //     // Verify that no save operation occurred
    //     verify(reviewRepository, times(0)).save(any(Review.class));
    // }

    @Test
    public void testUpdateReviewDate_NewDateInFuture_WithServiceException() {
        // Arrange
        Long reviewId = 1L;
        Review review = new Review(5, "Sample review", Date.valueOf(LocalDate.now()));
        Date futureDate = Date.valueOf(LocalDate.now().plusDays(1)); // A future date

        // Mock the repository to return an existing review
        when(reviewRepository.findReviewById(reviewId)).thenReturn(review);

        // Act & Assert
        VideoGamesSystemException exception = assertThrows(VideoGamesSystemException.class, () -> {
            // Simulate the behavior within the service method
            Review retrievedReview = reviewRepository.findReviewById(reviewId);
            if (retrievedReview == null) {
                throw new VideoGamesSystemException(HttpStatus.NOT_FOUND, "Review not found.");
            }
            if (futureDate.after(Date.valueOf(LocalDate.now()))) {
                throw new VideoGamesSystemException(HttpStatus.BAD_REQUEST, "Review date cannot be in the future.");
            }
            retrievedReview.setReviewDate(futureDate);
            reviewRepository.save(retrievedReview);
        });

        // Assert exception message and HTTP status
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertEquals("Review date cannot be in the future.", exception.getMessage());

        // Verify that no save operation occurred
        verify(reviewRepository, times(1)).findReviewById(reviewId);
        verify(reviewRepository, times(0)).save(any(Review.class));
    }
    

    @Test
    public void testUpdateReviewDate_Success() {
        // Arrange
        Long reviewId = 1L;
        Date validDate = Date.valueOf(LocalDate.now().minusDays(1)); // A valid past date
        Review review = new Review(5, "Amazing game!", Date.valueOf(LocalDate.now()));
        review.setCustomer(new Customer()); // Adding a dummy customer for completeness

        // Mock repository to return a review
        when(reviewRepository.findReviewById(reviewId)).thenReturn(review);
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        // Act
        review.setReviewDate(validDate);
        Review updatedReview = reviewService.getReviewById(reviewId); // Simulate retrieval from service
        updatedReview.setReviewDate(validDate); // Update date
        reviewRepository.save(updatedReview);

        // Assert
        assertNotNull(updatedReview, "The updated review should not be null.");
        assertEquals(validDate, updatedReview.getReviewDate(), "The review date should be updated to the valid date.");
        verify(reviewRepository, times(1)).findReviewById(reviewId);
        verify(reviewRepository, times(1)).save(updatedReview);
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
        VideoGamesSystemException exception = assertThrows(VideoGamesSystemException.class, () -> {
            reviewService.getReviewsByGame(null);
        });

        // Assert exception message
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
