/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/
package ca.mcgill.ecse321.videogamessystem.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

// line 112 "model.ump"
// line 202 "model.ump"
@Entity
public class GameConsole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GameConsole Attributes
  @EmbeddedId
  private GameConsoleId gameConsoleID;

  //GameConsole Associations
  @ManyToOne
  @MapsId("consoleId") // Links consoleId in GameConsoleId with Console
  @JoinColumn(name = "console_id") // Maps to the primary key in Console
  private Console console;

  @ManyToOne
  @MapsId("gameId") // Links consoleId in GameConsoleId with Console
  @JoinColumn(name = "game_id") // Maps to the primary key in Console
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GameConsole(GameConsoleId aGameConsoleID)
  {
    gameConsoleID = aGameConsoleID;
    Console console = new Console();
    this.console = console;
    Game game = new Game();
    this.game = game;
  }

  public GameConsole(){
    
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGameConsoleID(GameConsoleId aGameConsoleID)
  {
    boolean wasSet = false;
    gameConsoleID = aGameConsoleID;
    wasSet = true;
    return wasSet;
  }

  public GameConsoleId getGameConsoleID()
  {
    return gameConsoleID;
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

  public void delete()
  {
    console = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "gameConsoleID" + ":" + getGameConsoleID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "console = "+(getConsole()!=null?Integer.toHexString(System.identityHashCode(getConsole())):"null");
  }
}