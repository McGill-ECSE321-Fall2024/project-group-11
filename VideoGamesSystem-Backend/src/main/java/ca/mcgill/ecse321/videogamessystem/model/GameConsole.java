/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/
package ca.mcgill.ecse321.videogamessystem.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

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
  private Console console;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GameConsole(GameConsoleId aGameConsoleID, Console aConsole)
  {
    gameConsoleID = aGameConsoleID;
    if (!setConsole(aConsole))
    {
      throw new RuntimeException("Unable to create GameConsole due to aConsole. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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
  /* Code from template association_GetOne */
  public Console getConsole()
  {
    return console;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setConsole(Console aNewConsole)
  {
    boolean wasSet = false;
    if (aNewConsole != null)
    {
      console = aNewConsole;
      wasSet = true;
    }
    return wasSet;
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