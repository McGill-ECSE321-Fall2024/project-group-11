/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



// line 110 "model.ump"
// line 202 "model.ump"
public class ConsoleType
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Status { Xbox, PS4, PC, Switch }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ConsoleType Attributes
  private Status status;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ConsoleType(Status aStatus)
  {
    status = aStatus;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStatus(Status aStatus)
  {
    boolean wasSet = false;
    status = aStatus;
    wasSet = true;
    return wasSet;
  }

  public Status getStatus()
  {
    return status;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "status" + "=" + (getStatus() != null ? !getStatus().equals(this)  ? getStatus().toString().replaceAll("  ","    ") : "this" : "null");
  }
}