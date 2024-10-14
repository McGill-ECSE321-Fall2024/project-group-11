package ca.mcgill.ecse321.videogamessystem.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// line 99 "model.ump"
// line 211 "model.ump"
@Entity
public class ReviewRating
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Status { Like, Dislike }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ReviewRating Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Status status;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ReviewRating(Status aStatus)
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