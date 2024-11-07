/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/
package ca.mcgill.ecse321.videogamessystem.model;

import jakarta.persistence.Entity;

// line 96 "model.ump"
// line 153 "model.ump"
@Entity
public class Staff extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Staff Attributes

  private StaffType staffType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Staff(String aUserName, String aEmail, String aPassword, StaffType aStaffType)
  {
    super(aUserName, aEmail, aPassword);
    staffType = aStaffType;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStaffType(StaffType aStaffType)
  {
    boolean wasSet = false;
    staffType = aStaffType;
    wasSet = true;
    return wasSet;
  }

  public StaffType getStaffType()
  {
    return staffType;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "staffType" + "=" + (getStaffType() != null ? !getStaffType().equals(this)  ? getStaffType().toString().replaceAll("  ","    ") : "this" : "null");
  }
}