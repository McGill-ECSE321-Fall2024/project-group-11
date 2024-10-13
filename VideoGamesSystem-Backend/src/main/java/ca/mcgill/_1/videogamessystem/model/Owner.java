package ca.mcgill._1.videogamessystem.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import java.sql.Date;

import jakarta.persistence.Entity;

// line 17 "model.ump"
// line 196 "model.ump"
@Entity
public class Owner extends Staff
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------


  public Owner(String aUserName, String aEmail, String aPassword, int aPhoneNumber, String aAddress, Date aCreationDate, int aIdNum) {
    super(aUserName, aEmail, aPassword, aPhoneNumber, aAddress, aCreationDate, aIdNum);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}