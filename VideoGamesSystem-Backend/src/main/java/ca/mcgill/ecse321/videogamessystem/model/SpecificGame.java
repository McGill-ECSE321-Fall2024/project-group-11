package ca.mcgill.ecse321.videogamessystem.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



// line 72 "model.ump"
// line 166 "model.ump"
public class SpecificGame
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SpecificGame Attributes
  private int serialNumber;

  //SpecificGame Associations
  private Game game;
  private Order order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificGame(int aSerialNumber, Game aGame)
  {
    serialNumber = aSerialNumber;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create specificGame due to game. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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

  public int getSerialNumber()
  {
    return serialNumber;
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
      existingGame.removeSpecificGame(this);
    }
    game.addSpecificGame(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setOrder(Order aOrder)
  {
    boolean wasSet = false;
    Order existingOrder = order;
    order = aOrder;
    if (existingOrder != null && !existingOrder.equals(aOrder))
    {
      existingOrder.removeSpecificGame(this);
    }
    if (aOrder != null)
    {
      aOrder.addSpecificGame(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeSpecificGame(this);
    }
    if (order != null)
    {
      Order placeholderOrder = order;
      this.order = null;
      placeholderOrder.removeSpecificGame(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "serialNumber" + ":" + getSerialNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null");
  }
}