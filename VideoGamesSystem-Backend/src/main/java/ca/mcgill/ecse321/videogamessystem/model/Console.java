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
  private ConsoleType consoleType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Console(ConsoleType aConsoleType)
  {
    consoleType = aConsoleType;

  }

  public Console(){

  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setConsoleType(ConsoleType aConsoleType)
  {
    boolean wasSet = false;
    consoleType = aConsoleType;
    wasSet = true;
    return wasSet;
  }

  public ConsoleType getConsoleType()
  {
    return consoleType;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "consoleType" + ":" + getConsoleType()+ "]";
  }

  // ------------------------
  // MEMBER VARIABLES
  // ------------------------
  public enum ConsoleType {
    PS4,
    XBOX,
    Switch,
    Wii,
    PC,
    Other;

    @Override
    public String toString() {
      return name();
    }
  }
  
}

