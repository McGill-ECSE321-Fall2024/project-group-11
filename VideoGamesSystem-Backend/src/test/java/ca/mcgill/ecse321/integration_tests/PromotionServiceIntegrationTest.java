import static org.junit.jupiter.api.Assertions.*;

import ca.mcgill.ecse321.videogamessystem.model.Promotion;
import ca.mcgill.ecse321.videogamessystem.service.PromotionService;
import ca.mcgill.ecse321.videogamessystem.repository.PromotionRepository;
import ca.mcgill.ecse321.videogamessystem.VideogamessystemApplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest(classes = VideogamessystemApplication.class)
@Transactional
public class PromotionServiceIntegrationTest {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private PromotionRepository promotionRepository;

    @Test
    public void testCreatePromotion_Success() {
        // Arrange
        int percentage = 20;
        Date startDate = Date.valueOf(LocalDate.now());
        Date endDate = Date.valueOf(LocalDate.now().plusDays(10));

        // Act
        Promotion createdPromotion = promotionService.createPromotion(percentage, startDate, endDate);

        // Assert
        assertNotNull(createdPromotion.getId());
        assertEquals(percentage, createdPromotion.getPercentage());
        assertEquals(startDate, createdPromotion.getStartDate());
        assertEquals(endDate, createdPromotion.getEndDate());

        // Clean-up (Optional: Since @Transactional rolls back changes)
        promotionRepository.delete(createdPromotion);
    }

    @Test
    public void testGetPromotionById() {
        // Arrange
        Promotion promotion = new Promotion(15, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(5)));
        Promotion savedPromotion = promotionRepository.save(promotion);

        // Act
        Promotion foundPromotion = promotionService.getPromotionById(savedPromotion.getId());

        // Assert
        assertNotNull(foundPromotion);
        assertEquals(savedPromotion.getId(), foundPromotion.getId());
        assertEquals(savedPromotion.getPercentage(), foundPromotion.getPercentage());
    }

    @Test
    public void testUpdatePromotionPercentage() {
        // Arrange
        Promotion promotion = new Promotion(15, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(5)));
        Promotion savedPromotion = promotionRepository.save(promotion);
        int newPercentage = 25;

        // Act
        Promotion updatedPromotion = promotionService.updatePromotioPercentage(savedPromotion.getId(), newPercentage);

        // Assert
        assertEquals(newPercentage, updatedPromotion.getPercentage());
    }

    @Test
    public void testDeletePromotion() {
        // Arrange
        Promotion promotion = new Promotion(10, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(7)));
        Promotion savedPromotion = promotionRepository.save(promotion);

        // Act
        promotionService.deletePromotion(savedPromotion.getId());

        // Assert
        assertFalse(promotionRepository.existsById(savedPromotion.getId()));
    }

    @Test
    public void testGetAllPromotions() {
        // Arrange
        Promotion promotion1 = new Promotion(10, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(5)));
        Promotion promotion2 = new Promotion(20, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(10)));
        promotionRepository.save(promotion1);
        promotionRepository.save(promotion2);

        // Act
        List<Promotion> promotions = promotionService.getAllPromotion();

        // Assert
        assertTrue(promotions.size() >= 2); // At least the two we added
    }
}
