package ca.mcgill.ecse321.videogamessystem.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



// line 95 "model.ump"
// line 156 "model.ump"
public class StaffType
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Status { Owner, Employee }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //StaffType Attributes
  private Status status;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public StaffType(Status aStatus)
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