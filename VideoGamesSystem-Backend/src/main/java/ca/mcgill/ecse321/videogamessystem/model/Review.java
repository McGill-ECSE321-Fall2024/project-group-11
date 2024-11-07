package ca.mcgill.ecse321.videogamessystem.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;

// line 47 "model.ump"
// line 172 "model.ump"
@Entity
public class Review
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Review Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int gameRating;
  private String reviewContent;
  private Date reviewDate;

  //Review Associations
  @ManyToOne
  private Review replies;
  @ManyToOne
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Review(int aGameRating, String aReviewContent, Date aReviewDate, Customer aCustomer)
  {
    gameRating = aGameRating;
    reviewContent = aReviewContent;
    reviewDate = aReviewDate;
    if (!setCustomer(aCustomer))
    {
      throw new RuntimeException("Unable to create Review due to aCustomer. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGameRating(int aGameRating)
  {
    boolean wasSet = false;
    gameRating = aGameRating;
    wasSet = true;
    return wasSet;
  }

  public boolean setReviewContent(String aReviewContent)
  {
    boolean wasSet = false;
    reviewContent = aReviewContent;
    wasSet = true;
    return wasSet;
  }

  public boolean setReviewDate(Date aReviewDate)
  {
    boolean wasSet = false;
    reviewDate = aReviewDate;
    wasSet = true;
    return wasSet;
  }

  public int getGameRating()
  {
    return gameRating;
  }

  public String getReviewContent()
  {
    return reviewContent;
  }

  public Date getReviewDate()
  {
    return reviewDate;
  }
  /* Code from template association_GetOne */
  public Review getReplies()
  {
    return replies;
  }

  public boolean hasReplies()
  {
    boolean has = replies != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setReplies(Review aNewReplies)
  {
    boolean wasSet = false;
    replies = aNewReplies;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setCustomer(Customer aNewCustomer)
  {
    boolean wasSet = false;
    if (aNewCustomer != null)
    {
      customer = aNewCustomer;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    replies = null;
    customer = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "gameRating" + ":" + getGameRating()+ "," +
            "reviewContent" + ":" + getReviewContent()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "reviewDate" + "=" + (getReviewDate() != null ? !getReviewDate().equals(this)  ? getReviewDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }
}