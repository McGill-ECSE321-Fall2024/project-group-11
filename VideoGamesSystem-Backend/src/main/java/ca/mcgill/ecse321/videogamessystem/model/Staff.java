/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



// line 83 "model.ump"
// line 142 "model.ump"
public class Staff extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Staff Attributes
  private boolean staffType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Staff(String aUserName, String aEmail, String aPassword, boolean aStaffType)
  {
    super(aUserName, aEmail, aPassword);
    staffType = aStaffType;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStaffType(boolean aStaffType)
  {
    boolean wasSet = false;
    staffType = aStaffType;
    wasSet = true;
    return wasSet;
  }

  public boolean getStaffType()
  {
    return staffType;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isStaffType()
  {
    return staffType;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "staffType" + ":" + getStaffType()+ "]";
  }
}