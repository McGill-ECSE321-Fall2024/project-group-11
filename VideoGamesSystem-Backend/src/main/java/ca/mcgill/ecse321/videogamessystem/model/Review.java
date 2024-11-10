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
  private Long id;
  
  private int gameRating;
  private String reviewContent;
  private Date reviewDate;

  //Review Associations
  @ManyToOne
  private Review parentReview;
  @ManyToOne
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Review(int aGameRating, String aReviewContent, Date aReviewDate)
  {
    gameRating = aGameRating;
    reviewContent = aReviewContent;
    reviewDate = aReviewDate;
    Customer customer = new Customer();
    this.customer = customer;
    Review parentReview = new Review();
    this.parentReview = parentReview;
  }

  public Review(){

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
  public Review getParentReview()
  {
    return parentReview;
  }

  public boolean hasParentReview()
  {
    boolean has = parentReview != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setParentReview(Review aNewParentReview)
  {
    boolean wasSet = false;
    parentReview = aNewParentReview;
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
    parentReview = null;
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