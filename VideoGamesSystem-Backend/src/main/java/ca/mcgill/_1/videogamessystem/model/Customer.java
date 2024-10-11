/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;
import java.sql.Date;

// line 2 "model.ump"
// line 190 "model.ump"
public class Customer extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Associations
  private List<Wishlist> wishlist;
  private List<Review> review;
  private List<Order> order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(String aUserName)
  {
    super(aUserName);
    wishlist = new ArrayList<Wishlist>();
    review = new ArrayList<Review>();
    order = new ArrayList<Order>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Wishlist getWishlist(int index)
  {
    Wishlist aWishlist = wishlist.get(index);
    return aWishlist;
  }

  public List<Wishlist> getWishlist()
  {
    List<Wishlist> newWishlist = Collections.unmodifiableList(wishlist);
    return newWishlist;
  }

  public int numberOfWishlist()
  {
    int number = wishlist.size();
    return number;
  }

  public boolean hasWishlist()
  {
    boolean has = wishlist.size() > 0;
    return has;
  }

  public int indexOfWishlist(Wishlist aWishlist)
  {
    int index = wishlist.indexOf(aWishlist);
    return index;
  }
  /* Code from template association_GetMany */
  public Review getReview(int index)
  {
    Review aReview = review.get(index);
    return aReview;
  }

  public List<Review> getReview()
  {
    List<Review> newReview = Collections.unmodifiableList(review);
    return newReview;
  }

  public int numberOfReview()
  {
    int number = review.size();
    return number;
  }

  public boolean hasReview()
  {
    boolean has = review.size() > 0;
    return has;
  }

  public int indexOfReview(Review aReview)
  {
    int index = review.indexOf(aReview);
    return index;
  }
  /* Code from template association_GetMany */
  public Order getOrder(int index)
  {
    Order aOrder = order.get(index);
    return aOrder;
  }

  public List<Order> getOrder()
  {
    List<Order> newOrder = Collections.unmodifiableList(order);
    return newOrder;
  }

  public int numberOfOrder()
  {
    int number = order.size();
    return number;
  }

  public boolean hasOrder()
  {
    boolean has = order.size() > 0;
    return has;
  }

  public int indexOfOrder(Order aOrder)
  {
    int index = order.indexOf(aOrder);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWishlist()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Wishlist addWishlist()
  {
    return new Wishlist(this);
  }

  public boolean addWishlist(Wishlist aWishlist)
  {
    boolean wasAdded = false;
    if (wishlist.contains(aWishlist)) { return false; }
    Customer existingCustomer = aWishlist.getCustomer();
    boolean isNewCustomer = existingCustomer != null && !this.equals(existingCustomer);
    if (isNewCustomer)
    {
      aWishlist.setCustomer(this);
    }
    else
    {
      wishlist.add(aWishlist);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWishlist(Wishlist aWishlist)
  {
    boolean wasRemoved = false;
    //Unable to remove aWishlist, as it must always have a customer
    if (!this.equals(aWishlist.getCustomer()))
    {
      wishlist.remove(aWishlist);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWishlistAt(Wishlist aWishlist, int index)
  {  
    boolean wasAdded = false;
    if(addWishlist(aWishlist))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWishlist()) { index = numberOfWishlist() - 1; }
      wishlist.remove(aWishlist);
      wishlist.add(index, aWishlist);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWishlistAt(Wishlist aWishlist, int index)
  {
    boolean wasAdded = false;
    if(wishlist.contains(aWishlist))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWishlist()) { index = numberOfWishlist() - 1; }
      wishlist.remove(aWishlist);
      wishlist.add(index, aWishlist);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWishlistAt(aWishlist, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReview()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Review addReview(int aRating, String aContent, Game aGame)
  {
    return new Review(aRating, aContent, this, aGame);
  }

  public boolean addReview(Review aReview)
  {
    boolean wasAdded = false;
    if (review.contains(aReview)) { return false; }
    Customer existingCustomer = aReview.getCustomer();
    boolean isNewCustomer = existingCustomer != null && !this.equals(existingCustomer);
    if (isNewCustomer)
    {
      aReview.setCustomer(this);
    }
    else
    {
      review.add(aReview);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReview(Review aReview)
  {
    boolean wasRemoved = false;
    //Unable to remove aReview, as it must always have a customer
    if (!this.equals(aReview.getCustomer()))
    {
      review.remove(aReview);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addReviewAt(Review aReview, int index)
  {  
    boolean wasAdded = false;
    if(addReview(aReview))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReview()) { index = numberOfReview() - 1; }
      review.remove(aReview);
      review.add(index, aReview);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReviewAt(Review aReview, int index)
  {
    boolean wasAdded = false;
    if(review.contains(aReview))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReview()) { index = numberOfReview() - 1; }
      review.remove(aReview);
      review.add(index, aReview);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReviewAt(aReview, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrder()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Order addOrder(int aNumber, Date aDate, Cart aCart, SpecificGame... allSpecificGames)
  {
    return new Order(aNumber, aDate, this, aCart, allSpecificGames);
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (order.contains(aOrder)) { return false; }
    Customer existingCustomer = aOrder.getCustomer();
    boolean isNewCustomer = existingCustomer != null && !this.equals(existingCustomer);
    if (isNewCustomer)
    {
      aOrder.setCustomer(this);
    }
    else
    {
      order.add(aOrder);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrder, as it must always have a customer
    if (!this.equals(aOrder.getCustomer()))
    {
      order.remove(aOrder);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOrderAt(Order aOrder, int index)
  {  
    boolean wasAdded = false;
    if(addOrder(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrder()) { index = numberOfOrder() - 1; }
      order.remove(aOrder);
      order.add(index, aOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderAt(Order aOrder, int index)
  {
    boolean wasAdded = false;
    if(order.contains(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrder()) { index = numberOfOrder() - 1; }
      order.remove(aOrder);
      order.add(index, aOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderAt(aOrder, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (wishlist.size() > 0)
    {
      Wishlist aWishlist = wishlist.get(wishlist.size() - 1);
      aWishlist.delete();
      wishlist.remove(aWishlist);
    }
    
    while (review.size() > 0)
    {
      Review aReview = review.get(review.size() - 1);
      aReview.delete();
      review.remove(aReview);
    }
    
    while (order.size() > 0)
    {
      Order aOrder = order.get(order.size() - 1);
      aOrder.delete();
      order.remove(aOrder);
    }
    
    super.delete();
  }

}