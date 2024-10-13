package ca.mcgill._1.videogamessystem.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;
import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

// line 88 "model.ump"
// line 140 "model.ump"
@Entity
public class Review
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Review Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int rating;
  private String content;

  //Review Associations
  @OneToMany
  private List<Reply> reply;
  @OneToOne
  private Customer customer;
  @OneToOne
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Review(int aRating, String aContent, Customer aCustomer, Game aGame)
  {
    rating = aRating;
    content = aContent;
    reply = new ArrayList<Reply>();
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create review due to customer. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create review due to game. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRating(int aRating)
  {
    boolean wasSet = false;
    rating = aRating;
    wasSet = true;
    return wasSet;
  }

  public boolean setContent(String aContent)
  {
    boolean wasSet = false;
    content = aContent;
    wasSet = true;
    return wasSet;
  }

  public int getRating()
  {
    return rating;
  }

  public String getContent()
  {
    return content;
  }
  /* Code from template association_GetMany */
  public Reply getReply(int index)
  {
    Reply aReply = reply.get(index);
    return aReply;
  }

  public List<Reply> getReply()
  {
    List<Reply> newReply = Collections.unmodifiableList(reply);
    return newReply;
  }

  public int numberOfReply()
  {
    int number = reply.size();
    return number;
  }

  public boolean hasReply()
  {
    boolean has = reply.size() > 0;
    return has;
  }

  public int indexOfReply(Reply aReply)
  {
    int index = reply.indexOf(aReply);
    return index;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReply()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Reply addReply(String aReply, Date aReplyDate)
  {
    return new Reply(aReply, aReplyDate, this);
  }

  public boolean addReply(Reply aReply)
  {
    boolean wasAdded = false;
    if (reply.contains(aReply)) { return false; }
    Review existingReview = aReply.getReview();
    boolean isNewReview = existingReview != null && !this.equals(existingReview);
    if (isNewReview)
    {
      aReply.setReview(this);
    }
    else
    {
      reply.add(aReply);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReply(Reply aReply)
  {
    boolean wasRemoved = false;
    //Unable to remove aReply, as it must always have a review
    if (!this.equals(aReply.getReview()))
    {
      reply.remove(aReply);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addReplyAt(Reply aReply, int index)
  {  
    boolean wasAdded = false;
    if(addReply(aReply))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReply()) { index = numberOfReply() - 1; }
      reply.remove(aReply);
      reply.add(index, aReply);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReplyAt(Reply aReply, int index)
  {
    boolean wasAdded = false;
    if(reply.contains(aReply))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReply()) { index = numberOfReply() - 1; }
      reply.remove(aReply);
      reply.add(index, aReply);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReplyAt(aReply, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCustomer(Customer aCustomer)
  {
    boolean wasSet = false;
    if (aCustomer == null)
    {
      return wasSet;
    }

    Customer existingCustomer = customer;
    customer = aCustomer;
    if (existingCustomer != null && !existingCustomer.equals(aCustomer))
    {
      existingCustomer.removeReview(this);
    }
    customer.addReview(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    if (aGame == null)
    {
      return wasSet;
    }

    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      existingGame.removeReview(this);
    }
    game.addReview(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=reply.size(); i > 0; i--)
    {
      Reply aReply = reply.get(i - 1);
      aReply.delete();
    }
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeReview(this);
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeReview(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "rating" + ":" + getRating()+ "," +
            "content" + ":" + getContent()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}