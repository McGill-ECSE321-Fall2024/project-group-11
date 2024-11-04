/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.sql.Date;
import java.util.*;

// line 32 "model.ump"
// line 140 "model.ump"
public class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private int number;
  private Date orderDate;
  private int cardNumber;
  private int orderID;

  //Order Associations
  private List<SpecificGame> specificGames;
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(int aNumber, Date aOrderDate, int aCardNumber, int aOrderID, Customer aCustomer, SpecificGame... allSpecificGames)
  {
    number = aNumber;
    orderDate = aOrderDate;
    cardNumber = aCardNumber;
    orderID = aOrderID;
    specificGames = new ArrayList<SpecificGame>();
    boolean didAddSpecificGames = setSpecificGames(allSpecificGames);
    if (!didAddSpecificGames)
    {
      throw new RuntimeException("Unable to create Order, must have at least 1 specificGames. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setCustomer(aCustomer))
    {
      throw new RuntimeException("Unable to create Order due to aCustomer. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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

  public boolean setOrderDate(Date aOrderDate)
  {
    boolean wasSet = false;
    orderDate = aOrderDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setCardNumber(int aCardNumber)
  {
    boolean wasSet = false;
    cardNumber = aCardNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setOrderID(int aOrderID)
  {
    boolean wasSet = false;
    orderID = aOrderID;
    wasSet = true;
    return wasSet;
  }

  public int getNumber()
  {
    return number;
  }

  public Date getOrderDate()
  {
    return orderDate;
  }

  public int getCardNumber()
  {
    return cardNumber;
  }

  public int getOrderID()
  {
    return orderID;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpecificGames()
  {
    return 1;
  }
  /* Code from template association_AddMNToOptionalOne */
  public boolean addSpecificGame(SpecificGame aSpecificGame)
  {
    boolean wasAdded = false;
    if (specificGames.contains(aSpecificGame)) { return false; }
    Order existingOrder = aSpecificGame.getOrder();
    if (existingOrder != null && existingOrder.numberOfSpecificGames() <= minimumNumberOfSpecificGames())
    {
      return wasAdded;
    }
    else if (existingOrder != null)
    {
      existingOrder.specificGames.remove(aSpecificGame);
    }
    specificGames.add(aSpecificGame);
    setOrder(aSpecificGame,this);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSpecificGame(SpecificGame aSpecificGame)
  {
    boolean wasRemoved = false;
    if (specificGames.contains(aSpecificGame) && numberOfSpecificGames() > minimumNumberOfSpecificGames())
    {
      specificGames.remove(aSpecificGame);
      setOrder(aSpecificGame,null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_SetMNToOptionalOne */
  public boolean setSpecificGames(SpecificGame... newSpecificGames)
  {
    boolean wasSet = false;
    if (newSpecificGames.length < minimumNumberOfSpecificGames())
    {
      return wasSet;
    }

    ArrayList<SpecificGame> checkNewSpecificGames = new ArrayList<SpecificGame>();
    HashMap<Order,Integer> orderToNewSpecificGames = new HashMap<Order,Integer>();
    for (SpecificGame aSpecificGame : newSpecificGames)
    {
      if (checkNewSpecificGames.contains(aSpecificGame))
      {
        return wasSet;
      }
      else if (aSpecificGame.getOrder() != null && !this.equals(aSpecificGame.getOrder()))
      {
        Order existingOrder = aSpecificGame.getOrder();
        if (!orderToNewSpecificGames.containsKey(existingOrder))
        {
          orderToNewSpecificGames.put(existingOrder, Integer.valueOf(existingOrder.numberOfSpecificGames()));
        }
        Integer currentCount = orderToNewSpecificGames.get(existingOrder);
        int nextCount = currentCount - 1;
        if (nextCount < 1)
        {
          return wasSet;
        }
        orderToNewSpecificGames.put(existingOrder, Integer.valueOf(nextCount));
      }
      checkNewSpecificGames.add(aSpecificGame);
    }

    specificGames.removeAll(checkNewSpecificGames);

    for (SpecificGame orphan : specificGames)
    {
      setOrder(orphan, null);
    }
    specificGames.clear();
    for (SpecificGame aSpecificGame : newSpecificGames)
    {
      if (aSpecificGame.getOrder() != null)
      {
        aSpecificGame.getOrder().specificGames.remove(aSpecificGame);
      }
      setOrder(aSpecificGame, this);
      specificGames.add(aSpecificGame);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_GetPrivate */
  private void setOrder(SpecificGame aSpecificGame, Order aOrder)
  {
    try
    {
      java.lang.reflect.Field mentorField = aSpecificGame.getClass().getDeclaredField("order");
      mentorField.setAccessible(true);
      mentorField.set(aSpecificGame, aOrder);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Issue internally setting aOrder to aSpecificGame", e);
    }
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
    for(SpecificGame aSpecificGame : specificGames)
    {
      setOrder(aSpecificGame,null);
    }
    specificGames.clear();
    customer = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "," +
            "cardNumber" + ":" + getCardNumber()+ "," +
            "orderID" + ":" + getOrderID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "orderDate" + "=" + (getOrderDate() != null ? !getOrderDate().equals(this)  ? getOrderDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }
}