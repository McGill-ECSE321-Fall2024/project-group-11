package ca.mcgill.ecse321.videogamessystem.dto.gameDto;

import ca.mcgill.ecse321.videogamessystem.model.Game.Category;
import ca.mcgill.ecse321.videogamessystem.model.Game.ConsoleType;
import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.dto.promotionDto.PromotionResponseDto;
import ca.mcgill.ecse321.videogamessystem.dto.WishlistDto.WishlistResponseDto;

public class GameResponseDto {
    private Long id;
    private String description;
    private int stockQuantity;
    private int price;
    private String title;
    private Category category;
    private ConsoleType consoleType;
/* 
    private PromotionResponseDto promotion;

    private WishlistResponseDto wishlist;
*/
    //maybe add associations

    public GameResponseDto(Game game) {
        this.id = game.getId();
        this.description = game.getDescription();
        this.stockQuantity = game.getStockQuantity();
        this.price = game.getPrice();
        this.title = game.getTitle();
        this.category = game.getCategory();
        this.consoleType = game.getConsoleType();
/* 
        this.promotion= PromotionResponseDto.convertToPromotionResponseDto(game.getPromotion());
        this.wishlist= WishlistResponseDto.convertToWishlistResponseDto(game.getWishlist());
*/
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getPrice() {
        return price;
    }


    public String getTitle() {
        return title;
    }

    public Category getCategory() {
        return category;
    }

    public ConsoleType getConsoleType() {
        return consoleType;
    }
/* 
    public WishlistResponseDto getWishlistResponseDto(){
        return this.wishlist;
    }

    public PromotionResponseDto getPromotionResponseDto(){
        return this.promotion;
    }
*/
}
