/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/
package ca.mcgill.ecse321.videogamessystem.model;


// line 62 "model.ump"
// line 178 "model.ump"
public class Console
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Console Attributes
  private String consoleType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Console(String aConsoleType)
  {
    consoleType = aConsoleType;
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

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "consoleType" + ":" + getConsoleType()+ "]";
  }
}