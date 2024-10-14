package ca.mcgill._1.videogamessystem.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class GameConsole {

    @EmbeddedId
    private GameConsoleId id;

    @ManyToOne
    @JoinColumn(name = "gameId", insertable = false, updatable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "consoleId", insertable = false, updatable = false)
    private Console console;

    // Constructors
    public GameConsole() {
    }

    public GameConsole(Game game, Console console) {
        this.game = game;
        this.console = console;
        this.id = new GameConsoleId(game.getId(), console.getId());
    }

    // Getters and setters
    public GameConsoleId getId() {
        return id;
    }

    public void setId(GameConsoleId id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public boolean removeConsole() {
        boolean wasRemoved = false;

        // Check if the GameConsole has an associated Console
        if (this.console != null) {
            Console oldConsole = this.console;

            // Remove the current GameConsole from the Console's list of GameConsoles
            oldConsole.getGameConsoles().remove(this);

            // Nullify the reference to the Console in this GameConsole
            this.console = null;

            wasRemoved = true;
        }
        return wasRemoved;
    }

    public boolean removeGame() {
        boolean wasRemoved = false;

        // Check if the GameConsole has an associated Game
        if (this.game != null) {
            Game oldGame = this.game;

            // Remove the current GameConsole from the Game's list of GameConsoles
            oldGame.getGameConsoles().remove(this);

            // Nullify the reference to the Game in this GameConsole
            this.game = null;

            wasRemoved = true;
        }

        return wasRemoved;
    }




}
