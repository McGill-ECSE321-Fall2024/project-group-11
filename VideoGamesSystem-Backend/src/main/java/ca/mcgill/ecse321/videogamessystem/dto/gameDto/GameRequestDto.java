package ca.mcgill.ecse321.videogamessystem.dto.gameDto;

import ca.mcgill.ecse321.videogamessystem.model.Game.Category;
import ca.mcgill.ecse321.videogamessystem.model.Game.ConsoleType;

public class GameRequestDto {
    private String description;
    private int stockQuantity;
    private int price;
    private String title;
    private Category category;
    private ConsoleType consoleType;

    //maybe add fo

    public GameRequestDto(String description, int stockQuantity, int price, String title, Category category, ConsoleType consoleType) {
        this.description = description;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.title = title;
        this.category = category;
        this.consoleType = consoleType;
    }

    // Getters and Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ConsoleType getConsoleType() {
        return consoleType;
    }

    public void setConsoleType(ConsoleType consoleType) {
        this.consoleType = consoleType;
    }
}
