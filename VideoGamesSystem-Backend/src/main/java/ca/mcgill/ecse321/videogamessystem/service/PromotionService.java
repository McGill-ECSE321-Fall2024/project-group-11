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
    private PromotionRepository promotionRepository;

    private GameRepository gameRepository;

    @Autowired
    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

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

        return promotion;
    }

    @Transactional
    public List<Promotion> gePromotionByStartdate(Date startDate){
        return this.promotionRepository.findPromotionByStartDate(startDate);

    }


    @Transactional
    public Promotion getPromotionById(Long id){
        return this.promotionRepository.findPromotionById(id);
    }

    @Transactional
    public List<Promotion> getPromotionByStartdate(Date startDate){
        return this.promotionRepository.findPromotionByStartDate(startDate);
    }


    @Transactional
    public List<Promotion> getPromotionByEnddate(Date endDate){
        return this.promotionRepository.findPromotionByEndDate(endDate);
    }


    @Transactional
    public List<Promotion> getPromotionbyPercentage(int percentage){
        return this.promotionRepository.findPromotionByPercentage(percentage);
    }


    @Transactional
    public List<Promotion> getAllPromotion() {
        return toList(this.promotionRepository.findAll());
    }

    @Transactional
    public Promotion updatePromotioPercentage(Long id, int percentage){
        Promotion promotion = promotionRepository.findPromotionById(id);

        if (percentage <= 0 || percentage > 100) {
            throw new IllegalArgumentException("invalid percentage to update");
        }
        promotion.setPercentage(percentage);
        return promotionRepository.save(promotion);
    }

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

    
    @Transactional
    public Promotion deletePromotion(Long id){
        Promotion exsistingpromotion = this.getPromotionById(id);
        List<Game> games = gameRepository.findByPromotion(exsistingpromotion);
        for(Game game: games){
            game.setPromotion(null);
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








    
}

    


    



