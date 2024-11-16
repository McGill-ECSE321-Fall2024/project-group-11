package ca.mcgill.ecse321.videogamessystem.dto.SpecificGameDto;

import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.model.SpecificGame;

public class SpecificGameResponseDto {

    private boolean availability;
    private int serialNumber;
    private Long gameId;
    private String title;
    private String description;
    private int price;
    private Game.Category category;
    private Game.ConsoleType consoleType;

    // Constructor with all relevant attributes
    public SpecificGameResponseDto(boolean availability, int serialNumber, Long gameId, String title, String description, int price, Game.Category category, Game.ConsoleType consoleType) {
        this.availability = availability;
        this.serialNumber = serialNumber;
        this.gameId = gameId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.consoleType = consoleType;
    }

    // Constructor that initializes fields from a SpecificGame entity
    public SpecificGameResponseDto(SpecificGame specificGame) {
        this.availability = specificGame.getAvailability();
        this.serialNumber = specificGame.getSerialNumber();
        
        // Initialize fields from the associated Game entity
        Game game = specificGame.getGame();
        if (game != null) {
            this.gameId = game.getId();
            this.title = game.getTitle();
            this.description = game.getDescription();
            this.price = game.getPrice();
            this.category = game.getCategory();
            this.consoleType = game.getConsoleType();
        }
    }

    // Getters and Setters
    public boolean isAvailability() {
        return availability;
    }



    public int getSerialNumber() {
        return serialNumber;
    }



    public Long getGameId() {
        return gameId;
    }



    public String getTitle() {
        return title;
    }



    public String getDescription() {
        return description;
    }

 

    public int getPrice() {
        return price;
    }

  

    public Game.Category getCategory() {
        return category;
    }

   

    public Game.ConsoleType getConsoleType() {
        return consoleType;
    }

}