/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



// line 53 "model.ump"
// line 168 "model.ump"
public class Console
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Console Attributes
  private ConsoleType consoleType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Console(ConsoleType aConsoleType)
  {
    consoleType = aConsoleType;
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
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "consoleType" + "=" + (getConsoleType() != null ? !getConsoleType().equals(this)  ? getConsoleType().toString().replaceAll("  ","    ") : "this" : "null");
  }
}