package ca.mcgill.ecse321.videogamessystem.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;
import java.sql.Date;

// line 2 "model.ump"
// line 184 "model.ump"
public class Customer extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Associations
  private List<Review> reviews;
  private List<Order> orders;
  private List<Game> wishlist;
  private List<Game> cart;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(String aUserName, String aEmail, String aPassword, int aPhoneNumber, String aAdress)
  {
    super(aUserName, aEmail, aPassword, aPhoneNumber, aAdress);
    reviews = new ArrayList<Review>();
    orders = new ArrayList<Order>();
    wishlist = new ArrayList<Game>();
    cart = new ArrayList<Game>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Review getReview(int index)
  {
    Review aReview = reviews.get(index);
    return aReview;
  }

  public List<Review> getReviews()
  {
    List<Review> newReviews = Collections.unmodifiableList(reviews);
    return newReviews;
  }

  public int numberOfReviews()
  {
    int number = reviews.size();
    return number;
  }

  public boolean hasReviews()
  {
    boolean has = reviews.size() > 0;
    return has;
  }

  public int indexOfReview(Review aReview)
  {
    int index = reviews.indexOf(aReview);
    return index;
  }
  /* Code from template association_GetMany */
  public Order getOrder(int index)
  {
    Order aOrder = orders.get(index);
    return aOrder;
  }

  public List<Order> getOrders()
  {
    List<Order> newOrders = Collections.unmodifiableList(orders);
    return newOrders;
  }

  public int numberOfOrders()
  {
    int number = orders.size();
    return number;
  }

  public boolean hasOrders()
  {
    boolean has = orders.size() > 0;
    return has;
  }

  public int indexOfOrder(Order aOrder)
  {
    int index = orders.indexOf(aOrder);
    return index;
  }
  /* Code from template association_GetMany */
  public Game getWishlist(int index)
  {
    Game aWishlist = wishlist.get(index);
    return aWishlist;
  }

  public List<Game> getWishlist()
  {
    List<Game> newWishlist = Collections.unmodifiableList(wishlist);
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

  public int indexOfWishlist(Game aWishlist)
  {
    int index = wishlist.indexOf(aWishlist);
    return index;
  }
  /* Code from template association_GetMany */
  public Game getCart(int index)
  {
    Game aCart = cart.get(index);
    return aCart;
  }

  public List<Game> getCart()
  {
    List<Game> newCart = Collections.unmodifiableList(cart);
    return newCart;
  }

  public int numberOfCart()
  {
    int number = cart.size();
    return number;
  }

  public boolean hasCart()
  {
    boolean has = cart.size() > 0;
    return has;
  }

  public int indexOfCart(Game aCart)
  {
    int index = cart.indexOf(aCart);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReviews()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Review addReview(int aRating, String aContent, int aCardNumber, Game aGame)
  {
    return new Review(aRating, aContent, aCardNumber, this, aGame);
  }

  public boolean addReview(Review aReview)
  {
    boolean wasAdded = false;
    if (reviews.contains(aReview)) { return false; }
    Customer existingCustomer = aReview.getCustomer();
    boolean isNewCustomer = existingCustomer != null && !this.equals(existingCustomer);
    if (isNewCustomer)
    {
      aReview.setCustomer(this);
    }
    else
    {
      reviews.add(aReview);
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
      reviews.remove(aReview);
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
      if(index > numberOfReviews()) { index = numberOfReviews() - 1; }
      reviews.remove(aReview);
      reviews.add(index, aReview);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReviewAt(Review aReview, int index)
  {
    boolean wasAdded = false;
    if(reviews.contains(aReview))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReviews()) { index = numberOfReviews() - 1; }
      reviews.remove(aReview);
      reviews.add(index, aReview);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReviewAt(aReview, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrders()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Order addOrder(int aNumber, Date aDate, int aCardNumber)
  {
    return new Order(aNumber, aDate, aCardNumber, this);
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    Customer existingCustomer = aOrder.getCustomer();
    boolean isNewCustomer = existingCustomer != null && !this.equals(existingCustomer);
    if (isNewCustomer)
    {
      aOrder.setCustomer(this);
    }
    else
    {
      orders.add(aOrder);
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
      orders.remove(aOrder);
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
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderAt(Order aOrder, int index)
  {
    boolean wasAdded = false;
    if(orders.contains(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderAt(aOrder, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWishlist()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addWishlist(Game aWishlist)
  {
    boolean wasAdded = false;
    if (wishlist.contains(aWishlist)) { return false; }
    wishlist.add(aWishlist);
    if (aWishlist.indexOfWishlist(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aWishlist.addWishlist(this);
      if (!wasAdded)
      {
        wishlist.remove(aWishlist);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeWishlist(Game aWishlist)
  {
    boolean wasRemoved = false;
    if (!wishlist.contains(aWishlist))
    {
      return wasRemoved;
    }

    int oldIndex = wishlist.indexOf(aWishlist);
    wishlist.remove(oldIndex);
    if (aWishlist.indexOfWishlist(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aWishlist.removeWishlist(this);
      if (!wasRemoved)
      {
        wishlist.add(oldIndex,aWishlist);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWishlistAt(Game aWishlist, int index)
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

  public boolean addOrMoveWishlistAt(Game aWishlist, int index)
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
  public static int minimumNumberOfCart()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addCart(Game aCart)
  {
    boolean wasAdded = false;
    if (cart.contains(aCart)) { return false; }
    cart.add(aCart);
    if (aCart.indexOfCart(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aCart.addCart(this);
      if (!wasAdded)
      {
        cart.remove(aCart);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeCart(Game aCart)
  {
    boolean wasRemoved = false;
    if (!cart.contains(aCart))
    {
      return wasRemoved;
    }

    int oldIndex = cart.indexOf(aCart);
    cart.remove(oldIndex);
    if (aCart.indexOfCart(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aCart.removeCart(this);
      if (!wasRemoved)
      {
        cart.add(oldIndex,aCart);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCartAt(Game aCart, int index)
  {  
    boolean wasAdded = false;
    if(addCart(aCart))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCart()) { index = numberOfCart() - 1; }
      cart.remove(aCart);
      cart.add(index, aCart);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCartAt(Game aCart, int index)
  {
    boolean wasAdded = false;
    if(cart.contains(aCart))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCart()) { index = numberOfCart() - 1; }
      cart.remove(aCart);
      cart.add(index, aCart);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCartAt(aCart, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=reviews.size(); i > 0; i--)
    {
      Review aReview = reviews.get(i - 1);
      aReview.delete();
    }
    for(int i=orders.size(); i > 0; i--)
    {
      Order aOrder = orders.get(i - 1);
      aOrder.delete();
    }
    ArrayList<Game> copyOfWishlist = new ArrayList<Game>(wishlist);
    wishlist.clear();
    for(Game aWishlist : copyOfWishlist)
    {
      aWishlist.removeWishlist(this);
    }
    ArrayList<Game> copyOfCart = new ArrayList<Game>(cart);
    cart.clear();
    for(Game aCart : copyOfCart)
    {
      aCart.removeCart(this);
    }
    super.delete();
  }

}