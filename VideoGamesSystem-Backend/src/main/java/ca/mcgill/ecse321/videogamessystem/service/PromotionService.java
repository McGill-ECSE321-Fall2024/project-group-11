package ca.mcgill.ecse321.videogamessystem.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.model.Promotion;
import ca.mcgill.ecse321.videogamessystem.repository.GameRepository;
import ca.mcgill.ecse321.videogamessystem.repository.PromotionRepository;
import jakarta.transaction.Transactional;



@Service
public class PromotionService {
    
    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private GameRepository gameRepository;

    /**
     * @param percentage
     * @param startDate
     * @param endDate
     * @return
     */
    @Transactional
    public Promotion createPromotion(int percentage, Date startDate, Date endDate){
        Date now = Date.valueOf(LocalDate.now());
        if (endDate == null || startDate == null){
            throw new IllegalArgumentException("not this buddy");
        }
        if (startDate.before(now) || endDate.before(now)){
            throw new IllegalArgumentException("date have to today and afterwards");
        }

        if(percentage <= 0 || percentage > 100){
            throw new IllegalArgumentException("invalid percentage");
        }

        Promotion promotion = new Promotion();
        promotion.setPercentage(percentage);
        promotion.setEndDate(endDate);
        promotion.setStartDate(startDate);
        return promotionRepository.save(promotion);
    }


    /**
     * @param id
     * @return
     */
    @Transactional
    public Promotion getPromotionById(Long id) {
        Promotion promotion = this.promotionRepository.findPromotionById(id);
        if (promotion == null) {
            throw new IllegalArgumentException("Promotion not found");
        }
        return promotion;
    }
    

    /**
     * @param startDate
     * @return
     */
    @Transactional
    public List<Promotion> getPromotionByStartdate(Date startDate){
        return this.promotionRepository.findPromotionByStartDate(startDate);
    }


    /**
     * @param endDate
     * @return
     */
    @Transactional
    public List<Promotion> getPromotionByEnddate(Date endDate){
        return this.promotionRepository.findPromotionByEndDate(endDate);
    }


    /**
     * @param percentage
     * @return
     */
    @Transactional
    public List<Promotion> getPromotionbyPercentage(int percentage){
        return this.promotionRepository.findPromotionByPercentage(percentage);
    }


    /**
     * @return
     */
    @Transactional
    public List<Promotion> getAllPromotion() {
        return toList(this.promotionRepository.findAll());
    }

    /**
     * @param id
     * @param percentage
     * @return
     */
    @Transactional
    public Promotion updatePromotioPercentage(Long id, int percentage){
        Promotion promotion = promotionRepository.findPromotionById(id);

        if (percentage <= 0 || percentage > 100) {
            throw new IllegalArgumentException("invalid percentage to update");
        }
        promotion.setPercentage(percentage);
        return promotionRepository.save(promotion);
    }

    /**
     * @param id
     * @param startDate
     * @return
     */
    @Transactional
    public Promotion updatePromotionStartDate(Long id, Date startDate){
        Promotion promotion = promotionRepository.findPromotionById(id);

        Date now = Date.valueOf(LocalDate.now());
        if (startDate == null || startDate.before(now)){
            throw new IllegalArgumentException("update startDate invalid");
        }
        promotion.setStartDate(startDate);
        return promotionRepository.save(promotion);
    }

    /**
     * @param id
     * @param endDate
     * @return
     */
    @Transactional
    public Promotion updatePromotionEndDate(Long id, Date endDate){
        Promotion promotion = promotionRepository.findPromotionById(id);

        Date now = Date.valueOf(LocalDate.now());
        if (endDate == null || endDate.before(now)) {
            throw new IllegalArgumentException("update endDate invalid");
        }
        promotion.setEndDate(endDate);
        return promotionRepository.save(promotion);
    }

    
    /**
     * @param id
     * @return
     */
    @Transactional
    public Promotion deletePromotion(Long id){
        Promotion exsistingpromotion = this.getPromotionById(id);
        List<Game> games = gameRepository.findGameByPromotion(exsistingpromotion);
        for(Game game: games){
            game.setPromotion(null);
            gameRepository.save(game);
        }
        promotionRepository.delete(exsistingpromotion);
        return exsistingpromotion;
    }

    /**
     * Converts an {@code Iterable} to a {@code List}. Utility method to allow
     * easier manipulation
     * of the results of repository queries.
     *
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


// is promotion available
/**
 * @param id
 * @return
 */
public boolean promotionAvailable(Long id){
    boolean valid = false;
    Promotion promotion = promotionRepository.findPromotionById(id);
    if (promotion == null){
        throw new IllegalArgumentException("promotion not found");
    }
    Date endDate = promotion.getEndDate();
    Date startDate = promotion.getStartDate();
    Date now = Date.valueOf(LocalDate.now());
    if (endDate.after(now) && startDate.before(now)){
        valid = true;
    }
    if (endDate.equals(now) || startDate.equals(now)){
        valid = true;
    }
    return valid;
}
    
}
