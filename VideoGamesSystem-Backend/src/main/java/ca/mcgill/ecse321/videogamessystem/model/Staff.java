package ca.mcgill.ecse321.videogamessystem.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

// line 29 "model.ump"
// line 201 "model.ump"
@Entity
public abstract class Staff extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Staff Attributes
  @Id
  private int idNum;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  // Default constructor (required by Hibernate)
  public Staff() {
  }

  public Staff(String aUserName, String aEmail, String aPassword, int aPhoneNumber, String aAddress, Date aCreationDate, int aIdNum) {
    super(aUserName, aEmail, aPassword, aPhoneNumber, aAddress, aCreationDate);
    this.idNum = aIdNum;
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