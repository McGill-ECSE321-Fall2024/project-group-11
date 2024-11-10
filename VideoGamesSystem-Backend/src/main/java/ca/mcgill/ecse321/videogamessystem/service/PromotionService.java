package ca.mcgill.ecse321.videogamessystem.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.videogamessystem.model.Promotion;
import ca.mcgill.ecse321.videogamessystem.repository.PromotionRepository;
import jakarta.transaction.Transactional;

@Service
public class PromotionService {
    private PromotionRepository promotionRepository;

    @Autowired
    private void setPromotionRepository(PromotionRepository promotionRepository) {
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

        Promotion promotion = new Promotion(percentage, endDate, startDate);
        promotion.setPercentage(percentage);
        promotion.setEndDate(endDate);
        promotion.setStartDate(startDate);

        return promotion;
    }

    @Transactional
    public List<Promotion> gePromotionByStartdate(Date startDate){
        return this.promotionRepository.findPromotionBystartDate(startDate);

    }


    @Transactional
    public List<Promotion> gePromotionByEnddate(Date endDate){
        return this.promotionRepository.findPromotionByendDate(endDate);
    }


    @Transactional
    public List<Promotion> getPromotionbyPercentage(int percentage){
        return this.getPromotionbyPercentage(percentage);
    }


    @Transactional
    public Promotion updatePromotioPercentage(int newPercentage, Promotion promotion){

    }

    


    












    
}
