/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



// line 101 "model.ump"
// line 161 "model.ump"
public class Staff extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Staff Attributes
  private StaffType staffType;
  private int staffID;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Staff(String aUserName, StaffType aStaffType, int aStaffID)
  {
    super(aUserName);
    staffType = aStaffType;
    staffID = aStaffID;
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

  public boolean setStaffID(int aStaffID)
  {
    boolean wasSet = false;
    staffID = aStaffID;
    wasSet = true;
    return wasSet;
  }

  public StaffType getStaffType()
  {
    return staffType;
  }

  public int getStaffID()
  {
    return staffID;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "staffID" + ":" + getStaffID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "staffType" + "=" + (getStaffType() != null ? !getStaffType().equals(this)  ? getStaffType().toString().replaceAll("  ","    ") : "this" : "null");
  }
}