/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.sql.Date;
import java.util.*;

// line 61 "model.ump"
// line 166 "model.ump"
public class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private int number;
  private Date date;

  //Order Associations
  private List<SpecificGame> specificGames;
  private Customer customer;
  private Cart cart;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(int aNumber, Date aDate, Customer aCustomer, Cart aCart, SpecificGame... allSpecificGames)
  {
    number = aNumber;
    date = aDate;
    specificGames = new ArrayList<SpecificGame>();
    boolean didAddSpecificGames = setSpecificGames(allSpecificGames);
    if (!didAddSpecificGames)
    {
      throw new RuntimeException("Unable to create Order, must have at least 1 specificGames. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create order due to customer. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddCart = setCart(aCart);
    if (!didAddCart)
    {
      throw new RuntimeException("Unable to create order due to cart. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    number = aNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public int getNumber()
  {
    return number;
  }

  public Date getDate()
  {
    return date;
  }
  /* Code from template association_GetMany */
  public SpecificGame getSpecificGame(int index)
  {
    SpecificGame aSpecificGame = specificGames.get(index);
    return aSpecificGame;
  }

  public List<SpecificGame> getSpecificGames()
  {
    List<SpecificGame> newSpecificGames = Collections.unmodifiableList(specificGames);
    return newSpecificGames;
  }

  public int numberOfSpecificGames()
  {
    int number = specificGames.size();
    return number;
  }

  public boolean hasSpecificGames()
  {
    boolean has = specificGames.size() > 0;
    return has;
  }

  public int indexOfSpecificGame(SpecificGame aSpecificGame)
  {
    int index = specificGames.indexOf(aSpecificGame);
    return index;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_GetOne */
  public Cart getCart()
  {
    return cart;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfSpecificGamesValid()
  {
    boolean isValid = numberOfSpecificGames() >= minimumNumberOfSpecificGames();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpecificGames()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addSpecificGame(SpecificGame aSpecificGame)
  {
    boolean wasAdded = false;
    if (specificGames.contains(aSpecificGame)) { return false; }
    specificGames.add(aSpecificGame);
    if (aSpecificGame.indexOfOrder(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aSpecificGame.addOrder(this);
      if (!wasAdded)
      {
        specificGames.remove(aSpecificGame);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeSpecificGame(SpecificGame aSpecificGame)
  {
    boolean wasRemoved = false;
    if (!specificGames.contains(aSpecificGame))
    {
      return wasRemoved;
    }

    if (numberOfSpecificGames() <= minimumNumberOfSpecificGames())
    {
      return wasRemoved;
    }

    int oldIndex = specificGames.indexOf(aSpecificGame);
    specificGames.remove(oldIndex);
    if (aSpecificGame.indexOfOrder(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aSpecificGame.removeOrder(this);
      if (!wasRemoved)
      {
        specificGames.add(oldIndex,aSpecificGame);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setSpecificGames(SpecificGame... newSpecificGames)
  {
    boolean wasSet = false;
    ArrayList<SpecificGame> verifiedSpecificGames = new ArrayList<SpecificGame>();
    for (SpecificGame aSpecificGame : newSpecificGames)
    {
      if (verifiedSpecificGames.contains(aSpecificGame))
      {
        continue;
      }
      verifiedSpecificGames.add(aSpecificGame);
    }

    if (verifiedSpecificGames.size() != newSpecificGames.length || verifiedSpecificGames.size() < minimumNumberOfSpecificGames())
    {
      return wasSet;
    }

    ArrayList<SpecificGame> oldSpecificGames = new ArrayList<SpecificGame>(specificGames);
    specificGames.clear();
    for (SpecificGame aNewSpecificGame : verifiedSpecificGames)
    {
      specificGames.add(aNewSpecificGame);
      if (oldSpecificGames.contains(aNewSpecificGame))
      {
        oldSpecificGames.remove(aNewSpecificGame);
      }
      else
      {
        aNewSpecificGame.addOrder(this);
      }
    }

    for (SpecificGame anOldSpecificGame : oldSpecificGames)
    {
      anOldSpecificGame.removeOrder(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSpecificGameAt(SpecificGame aSpecificGame, int index)
  {  
    boolean wasAdded = false;
    if(addSpecificGame(aSpecificGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificGames()) { index = numberOfSpecificGames() - 1; }
      specificGames.remove(aSpecificGame);
      specificGames.add(index, aSpecificGame);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSpecificGameAt(SpecificGame aSpecificGame, int index)
  {
    boolean wasAdded = false;
    if(specificGames.contains(aSpecificGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificGames()) { index = numberOfSpecificGames() - 1; }
      specificGames.remove(aSpecificGame);
      specificGames.add(index, aSpecificGame);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSpecificGameAt(aSpecificGame, index);
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
      existingCustomer.removeOrder(this);
    }
    customer.addOrder(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setCart(Cart aCart)
  {
    boolean wasSet = false;
    //Must provide cart to order
    if (aCart == null)
    {
      return wasSet;
    }

    if (cart != null && cart.numberOfOrders() <= Cart.minimumNumberOfOrders())
    {
      return wasSet;
    }

    Cart existingCart = cart;
    cart = aCart;
    if (existingCart != null && !existingCart.equals(aCart))
    {
      boolean didRemove = existingCart.removeOrder(this);
      if (!didRemove)
      {
        cart = existingCart;
        return wasSet;
      }
    }
    cart.addOrder(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<SpecificGame> copyOfSpecificGames = new ArrayList<SpecificGame>(specificGames);
    specificGames.clear();
    for(SpecificGame aSpecificGame : copyOfSpecificGames)
    {
      aSpecificGame.removeOrder(this);
    }
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeOrder(this);
    }
    Cart placeholderCart = cart;
    this.cart = null;
    if(placeholderCart != null)
    {
      placeholderCart.removeOrder(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "cart = "+(getCart()!=null?Integer.toHexString(System.identityHashCode(getCart())):"null");
  }
}