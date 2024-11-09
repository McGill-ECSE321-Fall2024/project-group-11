/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.sql.Date;

// line 60 "model.ump"
// line 173 "model.ump"
public class Promotion
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Promotion Attributes
  private int percentage;
  private Date startDate;
  private Date endDate;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Promotion(int aPercentage, Date aStartDate, Date aEndDate)
  {
    percentage = aPercentage;
    startDate = aStartDate;
    endDate = aEndDate;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPercentage(int aPercentage)
  {
    boolean wasSet = false;
    percentage = aPercentage;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDate(Date aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public int getPercentage()
  {
    return percentage;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "percentage" + ":" + getPercentage()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}