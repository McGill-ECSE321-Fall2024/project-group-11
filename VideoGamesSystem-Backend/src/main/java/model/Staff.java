/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



// line 29 "model.ump"
// line 201 "model.ump"
public abstract class Staff extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Staff Attributes
  private int idNum;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Staff(String aUserName, int aIdNum)
  {
    super(aUserName);
    idNum = aIdNum;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIdNum(int aIdNum)
  {
    boolean wasSet = false;
    idNum = aIdNum;
    wasSet = true;
    return wasSet;
  }

  public int getIdNum()
  {
    return idNum;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "idNum" + ":" + getIdNum()+ "]";
  }
}