package ca.mcgill.ecse321.videogamessystem.dto.SpecificGameDto;

import ca.mcgill.ecse321.videogamessystem.model.SpecificGame;

public class SpecificGameResponseDto {

    private int serialNumber;
    private boolean availability;
   // private Long gameId;  // ID of the associated Game
   // private Long orderId; // ID of the associated Order (if any)

    // Constructor that accepts the SpecificGame model object
    public SpecificGameResponseDto(SpecificGame specificGame) {
        this.serialNumber = specificGame.getSerialNumber();
        this.availability = specificGame.getAvailability();
       // this.gameId = specificGame.getGame() != null ? specificGame.getGame().getId() : null;
       // this.orderId = specificGame.getOrder() != null ? specificGame.getOrder().getId() : null;
    }

    // Getters
    public int getSerialNumber() {
        return serialNumber;
    }

    public boolean isAvailability() {
        return availability;
    }
}

