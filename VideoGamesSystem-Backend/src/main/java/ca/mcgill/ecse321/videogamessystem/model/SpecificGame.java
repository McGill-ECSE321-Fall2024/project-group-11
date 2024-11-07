/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/
package ca.mcgill.ecse321.videogamessystem.model;


// line 41 "model.ump"
// line 138 "model.ump"
public class SpecificGame
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SpecificGame Attributes
  private int serialNumber;
  private boolean availability;

  //SpecificGame Associations
  private Game game;
  private Order order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificGame(int aSerialNumber, boolean aAvailability, Game aGame)
  {
    serialNumber = aSerialNumber;
    availability = aAvailability;
    if (!setGame(aGame))
    {
      throw new RuntimeException("Unable to create SpecificGame due to aGame. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSerialNumber(int aSerialNumber)
  {
    boolean wasSet = false;
    serialNumber = aSerialNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setAvailability(boolean aAvailability)
  {
    boolean wasSet = false;
    availability = aAvailability;
    wasSet = true;
    return wasSet;
  }

  public int getSerialNumber()
  {
    return serialNumber;
  }

  public boolean getAvailability()
  {
    return availability;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetOne */
  public Order getOrder()
  {
    return order;
  }

  public boolean hasOrder()
  {
    boolean has = order != null;
    return has;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setGame(Game aNewGame)
  {
    boolean wasSet = false;
    if (aNewGame != null)
    {
      game = aNewGame;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMandatoryMany */
  public boolean setOrder(Order aOrder)
  {
    //
    // This source of this source generation is association_SetOptionalOneToMandatoryMany.jet
    // This set file assumes the generation of a maximumNumberOfXXX method does not exist because 
    // it's not required (No upper bound)
    //   
    boolean wasSet = false;
    Order existingOrder = order;

    if (existingOrder == null)
    {
      if (aOrder != null)
      {
        if (aOrder.addSpecificGame(this))
        {
          existingOrder = aOrder;
          wasSet = true;
        }
      }
    } 
    else if (existingOrder != null)
    {
      if (aOrder == null)
      {
        if (existingOrder.minimumNumberOfSpecificGames() < existingOrder.numberOfSpecificGames())
        {
          existingOrder.removeSpecificGame(this);
          existingOrder = aOrder;  // aOrder == null
          wasSet = true;
        }
      } 
      else
      {
        if (existingOrder.minimumNumberOfSpecificGames() < existingOrder.numberOfSpecificGames())
        {
          existingOrder.removeSpecificGame(this);
          aOrder.addSpecificGame(this);
          existingOrder = aOrder;
          wasSet = true;
        }
      }
    }
    if (wasSet)
    {
      order = existingOrder;
    }
    return wasSet;
  }
  
  public void delete()
  {
    game = null;
    if (order != null)
    {
      if (order.numberOfSpecificGames() <= 1)
      {
        order.delete();
      }
      else
      {
        Order placeholderOrder = order;
        this.order = null;
        placeholderOrder.removeSpecificGame(this);
      }
    }
  }

  
  public String toString() {
    return super.toString() + "[" +
        "serialNumber" + ":" + getSerialNumber() + "," +
        "  " + "availability" + "=" + getAvailability() + System.getProperties().getProperty("line.separator") +
        "  " + "game = " + (getGame() != null ? Integer.toHexString(System.identityHashCode(getGame())) : "null")
        + System.getProperties().getProperty("line.separator") +
        "  " + "order = " + (getOrder() != null ? Integer.toHexString(System.identityHashCode(getOrder())) : "null");
  }
}