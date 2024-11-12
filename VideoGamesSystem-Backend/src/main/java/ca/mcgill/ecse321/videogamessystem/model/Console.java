/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/
package ca.mcgill.ecse321.videogamessystem.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

// line 62 "model.ump"
// line 178 "model.ump"
@Entity
public class Console
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Console Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String consoleType;

  @ManyToOne
  private GameConsole gameConsole;
  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Console(String aConsoleType)
  {
    consoleType = aConsoleType;
    GameConsole gameconsole = new GameConsole();
    this.gameConsole = gameconsole;
  }

  public Console(){

  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setConsoleType(String aConsoleType)
  {
    boolean wasSet = false;
    consoleType = aConsoleType;
    wasSet = true;
    return wasSet;
  }

  public String getConsoleType()
  {
    return consoleType;
  }

  public GameConsole getGameConsole() {
    return gameConsole;
  }

  public void setGameConsole(GameConsole aGameConsole) {
    this.gameConsole = aGameConsole;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "consoleType" + ":" + getConsoleType()+ "]";
  }
}