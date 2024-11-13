package ca.mcgill.ecse321.videogamessystem.dto.GameDto;

import ca.mcgill.ecse321.videogamessystem.model.Game.Category;
import ca.mcgill.ecse321.videogamessystem.model.Game.ConsoleType;

//import jakarta.validation.constraints.NotBlank;

public class GameRequestDto {
    //@NotBlank(message="Cannot be blank")
    private String description;
    private int price;
    private String title;
    private Category category;
    private ConsoleType consoleType;

    //maybe add fo

    public GameRequestDto(String description, int price, String title, Category category, ConsoleType consoleType) {
        this.description = description;
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
