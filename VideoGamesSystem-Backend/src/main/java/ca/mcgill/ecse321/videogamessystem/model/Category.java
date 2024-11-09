/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



// line 117 "model.ump"
// line 214 "model.ump"
public class Category
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Status { Fps, Rpg, Sports, Strategy }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Category Attributes
  private Status status;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Category(Status aStatus)
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