package ca.mcgill.ecse321.videogamessystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GameConsoleId implements Serializable {

    @Column(name = "game_id")  // Specify the column name explicitly
    private Long gameId;

    @Column(name = "console_id")  // Specify the column name explicitly
    private Long consoleId;

    // Default constructor
    public GameConsoleId() {
    }

    // Constructor
    public GameConsoleId(Long gameId, Long consoleId) {
        this.gameId = gameId;
        this.consoleId = consoleId;
    }

    // Getters and setters
    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getConsoleId() {
        return consoleId;
    }

    public void setConsoleId(Long consoleId) {
        this.consoleId = consoleId;
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GameConsoleId that = (GameConsoleId) o;
        return Objects.equals(gameId, that.gameId) &&
                Objects.equals(consoleId, that.consoleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, consoleId);
    }
}