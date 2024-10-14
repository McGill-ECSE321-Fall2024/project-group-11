package ca.mcgill._1.videogamessystem.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

// line 22 "model.ump"
// line 166 "model.ump"

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Id;
import java.sql.Date;

@MappedSuperclass
public abstract class Account {

  // ------------------------
  // MEMBER VARIABLES
  // ------------------------

  // Account Attributes
  @Id
  @GeneratedValue(generator = "account_id_sequence_generator", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "account_id_sequence_generator", sequenceName = "account_id_seq", allocationSize = 1)
  private Long id;
  
  private String userName;
  private String email;
  private String password;
  private int phoneNumber;
  private String address;
  private Date creationDate;

  // ------------------------
  // CONSTRUCTOR
  // ------------------------

  public Account() {
  }

  public Account(String aUserName, String aEmail, String aPassword, int aPhoneNumber, String aAddress, Date aCreationDate) {
    userName = aUserName;
    email = aEmail;
    password = aPassword;
    phoneNumber = aPhoneNumber;
    address = aAddress;
    creationDate = aCreationDate;
  }

  // ------------------------
  // INTERFACE
  // ------------------------

  public boolean setUserName(String aUserName) {
    boolean wasSet = false;
    userName = aUserName;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail) {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword) {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(int aPhoneNumber) {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setAdress(String aAdress) {
    boolean wasSet = false;
    address = aAdress;
    wasSet = true;
    return wasSet;
  }

  public boolean setCreationDate(Date aDate) {
    boolean wasSet = false;
    creationDate = aDate;
    wasSet = true;
    return wasSet;
  }


  public String getUserName() {
    return userName;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public int getPhoneNumber() {
    return phoneNumber;
  }

  public String getAdress() {
    return address;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void delete() {
  }

  public String toString() {
    return super.toString() + "[" +
        "userName" + ":" + getUserName() + "," +
        "email" + ":" + getEmail() + "," +
        "password" + ":" + getPassword() + "," +
        "phoneNumber" + ":" + getPhoneNumber() + "," +
        "adress" + ":" + getAdress() + "]" +
        "Date" + "=" + (getCreationDate() != null ? !getCreationDate().equals(this)  ? getCreationDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator");
  }
}