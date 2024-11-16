package ca.mcgill.ecse321.videogamessystem.dto.GameDto;

import java.util.List;


public class GameListDto {

     private List<GameSummaryDto> games;

    public GameListDto(List<GameSummaryDto> games) {
        this.games = games;
    }

    // Default protected constructor (for potential use by frameworks)
    protected void GameSummaryDto() {
    }

    // Getter for the list of games
    public List<GameSummaryDto> getGames() {
        return games;
    }

    // Setter for the list of games
    public void setGames(List<GameSummaryDto> games) {
        this.games = games;
    }

    // Method to get the number of games
    public Integer getNumberOfGames() {
        return games.size();
    }
    
}
