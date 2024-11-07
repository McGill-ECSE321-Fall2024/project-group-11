/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/
package ca.mcgill.ecse321.videogamessystem.model;

import java.sql.Date;
import java.util.*;

// line 47 "model.ump"
// line 172 "model.ump"
public class Review
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Review Attributes
  private int gameRating;
  private String reviewContent;
  private Date reviewDate;

  //Review Associations
  private Review repliedBy;
  private List<Review> replies;
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Review(int aGameRating, String aReviewContent, Date aReviewDate, Customer aCustomer)
  {
    gameRating = aGameRating;
    reviewContent = aReviewContent;
    reviewDate = aReviewDate;
    replies = new ArrayList<Review>();
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
  public Review getRepliedBy()
  {
    return repliedBy;
  }

  public boolean hasRepliedBy()
  {
    boolean has = repliedBy != null;
    return has;
  }
  /* Code from template association_GetMany */
  public Review getReply(int index)
  {
    Review aReply = replies.get(index);
    return aReply;
  }

  public List<Review> getReplies()
  {
    List<Review> newReplies = Collections.unmodifiableList(replies);
    return newReplies;
  }

  public int numberOfReplies()
  {
    int number = replies.size();
    return number;
  }

  public boolean hasReplies()
  {
    boolean has = replies.size() > 0;
    return has;
  }

  public int indexOfReply(Review aReply)
  {
    int index = replies.indexOf(aReply);
    return index;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setRepliedBy(Review aRepliedBy)
  {
    boolean wasSet = false;
    Review existingRepliedBy = repliedBy;
    repliedBy = aRepliedBy;
    if (existingRepliedBy != null && !existingRepliedBy.equals(aRepliedBy))
    {
      existingRepliedBy.removeReply(this);
    }
    if (aRepliedBy != null)
    {
      aRepliedBy.addReply(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReplies()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addReply(Review aReply)
  {
    boolean wasAdded = false;
    if (replies.contains(aReply)) { return false; }
    Review existingRepliedBy = aReply.getRepliedBy();
    if (existingRepliedBy == null)
    {
      aReply.setRepliedBy(this);
    }
    else if (!this.equals(existingRepliedBy))
    {
      existingRepliedBy.removeReply(aReply);
      addReply(aReply);
    }
    else
    {
      replies.add(aReply);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReply(Review aReply)
  {
    boolean wasRemoved = false;
    if (replies.contains(aReply))
    {
      replies.remove(aReply);
      aReply.setRepliedBy(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addReplyAt(Review aReply, int index)
  {  
    boolean wasAdded = false;
    if(addReply(aReply))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReplies()) { index = numberOfReplies() - 1; }
      replies.remove(aReply);
      replies.add(index, aReply);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReplyAt(Review aReply, int index)
  {
    boolean wasAdded = false;
    if(replies.contains(aReply))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReplies()) { index = numberOfReplies() - 1; }
      replies.remove(aReply);
      replies.add(index, aReply);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReplyAt(aReply, index);
    }
    return wasAdded;
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
    if (repliedBy != null)
    {
      Review placeholderRepliedBy = repliedBy;
      this.repliedBy = null;
      placeholderRepliedBy.removeReply(this);
    }
    while( !replies.isEmpty() )
    {
      replies.get(0).setRepliedBy(null);
    }
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