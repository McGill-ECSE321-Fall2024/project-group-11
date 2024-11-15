package ca.mcgill.ecse321.videogamessystem.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import ca.mcgill.ecse321.videogamessystem.model.Promotion;
import ca.mcgill.ecse321.videogamessystem.model.SpecificGame;
import ca.mcgill.ecse321.videogamessystem.repository.PromotionRepository;
import ca.mcgill.ecse321.videogamessystem.service.PromotionService;
import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.model.Game.ConsoleType;
import ca.mcgill.ecse321.videogamessystem.repository.GameRepository;
import ca.mcgill.ecse321.videogamessystem.model.Game.Category;
import ca.mcgill.ecse321.videogamessystem.repository.SpecificGameRepository;
import ca.mcgill.ecse321.videogamessystem.service.GameService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;


public class PromotionServiceTest {

    @Mock
    private PromotionRepository promotionRepository;

    @Mock
    private SpecificGameRepository specificGameRepository;

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    @InjectMocks
    private PromotionService promotionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePromotion_Success() {
        // Arrange
        int percentage = 20;
        Date startDate = Date.valueOf(LocalDate.now());
        Date endDate = Date.valueOf(LocalDate.now().plusDays(10));

        Promotion promotion = new Promotion(percentage, startDate, endDate);
        when(promotionRepository.save(any(Promotion.class))).thenReturn(promotion);

        // Act
        Promotion createdPromotion = promotionService.createPromotion(percentage, startDate, endDate);

        // Assert
        assertNotNull(createdPromotion);
        assertEquals(percentage, createdPromotion.getPercentage());
        assertEquals(startDate, createdPromotion.getStartDate());
        assertEquals(endDate, createdPromotion.getEndDate());
    }

    @Test
    public void testGetPromotionById_PromotionExists() {
        // Arrange
        Long promoId = 1L;
        Promotion promotion = new Promotion();
        when(promotionRepository.findPromotionById(promoId)).thenReturn(promotion);

        // Act
        Promotion foundPromotion = promotionService.getPromotionById(promoId);

        // Assert
        assertNotNull(foundPromotion);
        verify(promotionRepository, times(1)).findPromotionById(promoId);
    }

    @Test
    public void testGetPromotionById_PromotionNotFound() {
        // Arrange
        Long promoId = 1L;
        when(promotionRepository.findPromotionById(promoId)).thenReturn(null);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            promotionService.getPromotionById(promoId);
        });
        assertEquals("Promotion not found", exception.getMessage());
    }

    @Test
    public void testUpdatePromotionPercentage_Success() {
        // Arrange
        Long promoId = 1L;
        int newPercentage = 50;
        Promotion promotion = new Promotion(20, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(10)));

        when(promotionRepository.findPromotionById(promoId)).thenReturn(promotion);
        when(promotionRepository.save(promotion)).thenReturn(promotion);

        // Act
        Promotion updatedPromotion = promotionService.updatePromotioPercentage(promoId, newPercentage);

        // Assert
        assertNotNull(updatedPromotion);
        assertEquals(newPercentage, updatedPromotion.getPercentage());
    }

    @Test
    public void testPromotionAvailable_PromotionValid() {
        // Arrange
        Long promoId = 1L;
        Date startDate = Date.valueOf(LocalDate.now().minusDays(5));
        Date endDate = Date.valueOf(LocalDate.now().plusDays(5));
        Promotion promotion = new Promotion(20, startDate, endDate);

        when(promotionRepository.findPromotionById(promoId)).thenReturn(promotion);

        // Act
        boolean isAvailable = promotionService.promotionAvailable(promoId);

        // Assert
        assertTrue(isAvailable);
    }

    @Test
    public void testDeletePromotion_Success() {
        Long promoId = 1L;
        Promotion promotion = new Promotion();
        when(promotionRepository.findPromotionById(promoId)).thenReturn(promotion);
        
        List<Game> games = new ArrayList<>();
        when(gameRepository.findGameByPromotion(promotion)).thenReturn(games);

        Promotion deletedPromotion = promotionService.deletePromotion(promoId);

        assertNotNull(deletedPromotion);
        verify(promotionRepository, times(1)).delete(promotion);
    }
    // @Test
    // public void testUpdatePromotionStartDate_Success() {
    //     // Arrange
    //     Long promoId = 1L;
    //     Date newStartDate = Date.valueOf(LocalDate.now().minusDays(10));
    //     Date endDate = Date.valueOf(LocalDate.now().plusDays(5));
    //     Promotion promotion = new Promotion(20, Date.valueOf(LocalDate.now().minusDays(5)), endDate);

    //     when(promotionRepository.findPromotionById(promoId)).thenReturn(promotion);
    //     when(promotionRepository.save(promotion)).thenReturn(promotion);

    //     // Act
    //     Promotion updatedPromotion = promotionService.updatePromotionStartDate(promoId, newStartDate);

    //     // Assert
    //     assertNotNull(updatedPromotion);
    //     assertEquals(newStartDate, updatedPromotion.getStartDate());
    //     assertEquals(endDate, updatedPromotion.getEndDate()); // Ensure endDate remains the same
    //     verify(promotionRepository, times(1)).save(promotion);
    // }

    @Test
    public void testUpdatePromotionEndDate_Success() {
        // Arrange
        Long promoId = 1L;
        Date newEndDate = Date.valueOf(LocalDate.now().plusDays(15));
        Promotion promotion = new Promotion(20, Date.valueOf(LocalDate.now().minusDays(5)), Date.valueOf(LocalDate.now().plusDays(5)));

        when(promotionRepository.findPromotionById(promoId)).thenReturn(promotion);
        when(promotionRepository.save(promotion)).thenReturn(promotion);

        // Act
        Promotion updatedPromotion = promotionService.updatePromotionEndDate(promoId, newEndDate);

        // Assert
        assertNotNull(updatedPromotion);
        assertEquals(newEndDate, updatedPromotion.getEndDate());
        verify(promotionRepository, times(1)).save(promotion);
    }
    

}
