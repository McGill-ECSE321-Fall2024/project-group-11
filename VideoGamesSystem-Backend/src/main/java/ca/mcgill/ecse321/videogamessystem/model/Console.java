package ca.mcgill.ecse321.videogamessystem.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



// line 64 "model.ump"
// line 186 "model.ump"
public class Console
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Console Attributes
  private String consoleType;
  private int consoleID;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Console(String aConsoleType, int aConsoleID)
  {
    consoleType = aConsoleType;
    consoleID = aConsoleID;
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

  public boolean setConsoleID(int aConsoleID)
  {
    boolean wasSet = false;
    consoleID = aConsoleID;
    wasSet = true;
    return wasSet;
  }

  public String getConsoleType()
  {
    return consoleType;
  }

  public int getConsoleID()
  {
    return consoleID;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "consoleType" + ":" + getConsoleType()+ "," +
            "consoleID" + ":" + getConsoleID()+ "]";
  }
}