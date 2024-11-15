/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/
package ca.mcgill.ecse321.videogamessystem.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

// line 76 "model.ump"
// line 163 "model.ump"
@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Attributes

  private int phoneNumber;
  private String adress;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(String aUserName, String aEmail, String aPassword, int aPhoneNumber, String aAdress)
  {
    super(aUserName, aEmail, aPassword);
    phoneNumber = aPhoneNumber;
    adress = aAdress;
  }

  public Customer(){
    super();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPhoneNumber(int aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setAdress(String aAdress)
  {
    boolean wasSet = false;
    adress = aAdress;
    wasSet = true;
    return wasSet;
  }

  public Long getId(){
    return super.getId();
  }

  public int getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getAdress()
  {
    return adress;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "adress" + ":" + getAdress()+ "]" + System.getProperties().getProperty("line.separator");
  }
}