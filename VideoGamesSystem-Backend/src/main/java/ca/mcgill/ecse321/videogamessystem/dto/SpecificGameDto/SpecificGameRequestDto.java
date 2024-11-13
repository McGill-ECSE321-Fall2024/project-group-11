package ca.mcgill.ecse321.videogamessystem.dto.SpecificGameDto;


public class SpecificGameRequestDto {

    private boolean availability;
    private int serialNumber;  // ID of the associated Game

    // Constructor
    public SpecificGameRequestDto(boolean availability, int serialNumber) {
        this.availability = availability;
        this.serialNumber = serialNumber;
        
    }

    // Getters and Setters
    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public int getserialNumber() {
        return serialNumber;
    }

    public void setGameId(int serialNumber) {
        this.serialNumber = serialNumber;
    }

}

