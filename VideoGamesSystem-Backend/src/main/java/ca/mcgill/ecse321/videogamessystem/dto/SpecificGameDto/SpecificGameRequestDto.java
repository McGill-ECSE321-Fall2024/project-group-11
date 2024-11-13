package ca.mcgill.ecse321.videogamessystem.dto.SpecificGameDto;

import ca.mcgill.ecse321.videogamessystem.model.Game;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class SpecificGameRequestDto {

    @NotNull(message = "Availability status must be specified.")
    private boolean availability;

    @Positive(message = "Serial number must be positive.")
    private int serialNumber;  

    @NotNull(message = "Game object cannot be null.")
    private Game game;

    @NotNull(message = "Game ID cannot be null.")
    @Positive(message = "Game ID must be a positive number.")
    private Long gameId;

    @NotBlank(message = "Title cannot be blank.")
    private String title;

    @NotBlank(message = "Description cannot be blank.")
    private String description;

    @Positive(message = "Price must be a positive number.")
    private int price;

    @NotNull(message = "Category cannot be null.")
    private Game.Category category;

    @NotNull(message = "Console type cannot be null.")
    private Game.ConsoleType consoleType;

    // Constructor with all attributes
    public SpecificGameRequestDto(boolean availability, int serialNumber, Long gameId, String title, String description, int price, Game.Category category, Game.ConsoleType consoleType) {
        this.availability = availability;
        this.serialNumber = serialNumber;
        this.gameId = gameId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.consoleType = consoleType;
    }

    // Getters and Setters
    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Long getGameId() {
        return gameId;
    }


    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public Game.Category getCategory() {
        return category;
    }

    public void setCategory(Game.Category category) {
        this.category = category;
    }

    public Game.ConsoleType getConsoleType() {
        return consoleType;
    }

    public void setConsoleType(Game.ConsoleType consoleType) {
        this.consoleType = consoleType;
    }
}
