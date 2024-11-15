package ca.mcgill.ecse321.videogamessystem.controller;

import ca.mcgill.ecse321.videogamessystem.dto.PromotionDto.PromotionRequestDto;
import ca.mcgill.ecse321.videogamessystem.dto.PromotionDto.PromotionResponseDto;
import ca.mcgill.ecse321.videogamessystem.model.Promotion;
import ca.mcgill.ecse321.videogamessystem.service.PromotionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    /**
     * Create a new promotion.
     *
     * @param request the promotion request DTO containing the promotion details
     * @return the response DTO with the created promotion details
     */
    @PostMapping
    public PromotionResponseDto createPromotion(@Valid @RequestBody PromotionRequestDto request) {
        Promotion promotion = promotionService.createPromotion(
                request.getPercentage(),
                request.getStartDate(),
                request.getEndDate()
        );
        return new PromotionResponseDto(promotion);
    }

    /**
     * Retrieve a promotion by its ID.
     *
     * @param id the ID of the promotion
     * @return the response DTO with the promotion details
     */
    @GetMapping("/{id}")
    public PromotionResponseDto getPromotionById(@PathVariable Long id) {
        Promotion promotion = promotionService.getPromotionById(id);
        return new PromotionResponseDto(promotion);
    }

    /**
     * Retrieve all promotions.
     *
     * @return a list of response DTOs with the details of all promotions
     */
    @GetMapping
    public List<PromotionResponseDto> getAllPromotions() {
        return promotionService.getAllPromotion().stream()
                .map(PromotionResponseDto::new)
                .collect(Collectors.toList());
    }

    // /**
    //  * Retrieve promotions by start date.
    //  *
    //  * @param startDate the start date of the promotions to retrieve
    //  * @return a list of response DTOs with the details of the promotions
    //  */
    // @GetMapping("/startDate/{startDate}")
    // public List<PromotionResponseDto> getPromotionsByStartDate(@PathVariable Date startDate) {
    //     return promotionService.getPromotionByStartdate(startDate).stream()
    //             .map(PromotionResponseDto::new)
    //             .collect(Collectors.toList());
    // }

    // /**
    //  * Retrieve promotions by end date.
    //  *
    //  * @param endDate the end date of the promotions to retrieve
    //  * @return a list of response DTOs with the details of the promotions
    //  */
    // @GetMapping("/endDate/{endDate}")
    // public List<PromotionResponseDto> getPromotionsByEndDate(@PathVariable Date endDate) {
    //     return promotionService.getPromotionByEnddate(endDate).stream()
    //             .map(PromotionResponseDto::new)
    //             .collect(Collectors.toList());
    // }

    // /**
    //  * Retrieve promotions by percentage.
    //  *
    //  * @param percentage the percentage of the promotions to retrieve
    //  * @return a list of response DTOs with the details of the promotions
    //  */
    // @GetMapping("/percentage/{percentage}")
    // public List<PromotionResponseDto> getPromotionsByPercentage(@PathVariable int percentage) {
    //     return promotionService.getPromotionbyPercentage(percentage).stream()
    //             .map(PromotionResponseDto::new)
    //             .collect(Collectors.toList());
    // }

    // /**
    //  * Update the percentage of a promotion.
    //  *
    //  * @param id the ID of the promotion
    //  * @param percentage the new percentage for the promotion
    //  * @return the response DTO with the updated promotion details
    //  */
    // @PutMapping("/{id}/percentage")
    // public PromotionResponseDto updatePromotionPercentage(@PathVariable Long id, @RequestParam int percentage) {
    //     Promotion promotion = promotionService.updatePromotioPercentage(id, percentage);
    //     return new PromotionResponseDto(promotion);
    // }

    // /**
    //  * Update the start date of a promotion.
    //  *
    //  * @param id the ID of the promotion
    //  * @param startDate the new start date for the promotion
    //  * @return the response DTO with the updated promotion details
    //  */
    // @PutMapping("/{id}/startDate")
    // public PromotionResponseDto updatePromotionStartDate(@PathVariable Long id, @RequestParam Date startDate) {
    //     Promotion promotion = promotionService.updatePromotionStartDate(id, startDate);
    //     return new PromotionResponseDto(promotion);
    // }

    // /**
    //  * Update the end date of a promotion.
    //  *
    //  * @param id the ID of the promotion
    //  * @param endDate the new end date for the promotion
    //  * @return the response DTO with the updated promotion details
    //  */
    // @PutMapping("/{id}/endDate")
    // public PromotionResponseDto updatePromotionEndDate(@PathVariable Long id, @RequestParam Date endDate) {
    //     Promotion promotion = promotionService.updatePromotionEndDate(id, endDate);
    //     return new PromotionResponseDto(promotion);
    // }

    /**
     * Delete a promotion by its ID.
     *
     * @param id the ID of the promotion to delete
     */
    @DeleteMapping("/{id}")
    public void deletePromotion(@PathVariable Long id) {
        promotionService.deletePromotion(id);
    }

    /**
     * Check if a promotion is currently available.
     *
     * @param id the ID of the promotion
     * @return true if the promotion is currently available, false otherwise
     */
    @GetMapping("/{id}/available")
    public boolean isPromotionAvailable(@PathVariable Long id) {
        return promotionService.promotionAvailable(id);
    }
    // @GetMapping("/{id}/available")
    // public ResponseEntity<Boolean> isPromotionAvailable(@PathVariable Long id) {
    //     boolean isAvailable = promotionService.promotionAvailable(id);
    //     return ResponseEntity.ok(isAvailable);
// }

}

