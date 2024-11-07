package ca.mcgill.ecse321.videogamessystem.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



// line 17 "model.ump"
// line 196 "model.ump"
public class Game
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private String description;
  private int stockQantity;
  private GameStatus gamestatus;
  private int price;
  private String title;
  private String category;
  private int gameID;

  //Game Associations
  private Wishlist wishlist;
  private GameConsole gameConsole;
  private Promotion promotion;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(String aDescription, int aStockQantity, GameStatus aGamestatus, int aPrice, String aTitle, String aCategory, int aGameID, Wishlist aWishlist, GameConsole aGameConsole, Promotion aPromotion)
  {
    description = aDescription;
    stockQantity = aStockQantity;
    gamestatus = aGamestatus;
    price = aPrice;
    title = aTitle;
    category = aCategory;
    gameID = aGameID;
    if (!setWishlist(aWishlist))
    {
      throw new RuntimeException("Unable to create Game due to aWishlist. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setGameConsole(aGameConsole))
    {
      throw new RuntimeException("Unable to create Game due to aGameConsole. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setPromotion(aPromotion))
    {
      throw new RuntimeException("Unable to create Game due to aPromotion. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setStockQantity(int aStockQantity)
  {
    boolean wasSet = false;
    stockQantity = aStockQantity;
    wasSet = true;
    return wasSet;
  }

  public boolean setGamestatus(GameStatus aGamestatus)
  {
    boolean wasSet = false;
    gamestatus = aGamestatus;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setCategory(String aCategory)
  {
    boolean wasSet = false;
    category = aCategory;
    wasSet = true;
    return wasSet;
  }

  public boolean setGameID(int aGameID)
  {
    boolean wasSet = false;
    gameID = aGameID;
    wasSet = true;
    return wasSet;
  }

  public String getDescription()
  {
    return description;
  }

  public int getStockQantity()
  {
    return stockQantity;
  }

  public GameStatus getGamestatus()
  {
    return gamestatus;
  }

  public int getPrice()
  {
    return price;
  }

  public String getTitle()
  {
    return title;
  }

  public String getCategory()
  {
    return category;
  }

  public int getGameID()
  {
    return gameID;
  }
  /* Code from template association_GetOne */
  public Wishlist getWishlist()
  {
    return wishlist;
  }
  /* Code from template association_GetOne */
  public GameConsole getGameConsole()
  {
    return gameConsole;
  }
  /* Code from template association_GetOne */
  public Promotion getPromotion()
  {
    return promotion;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setWishlist(Wishlist aNewWishlist)
  {
    boolean wasSet = false;
    if (aNewWishlist != null)
    {
      wishlist = aNewWishlist;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setGameConsole(GameConsole aNewGameConsole)
  {
    boolean wasSet = false;
    if (aNewGameConsole != null)
    {
      gameConsole = aNewGameConsole;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setPromotion(Promotion aNewPromotion)
  {
    boolean wasSet = false;
    if (aNewPromotion != null)
    {
      promotion = aNewPromotion;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    wishlist = null;
    gameConsole = null;
    promotion = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "description" + ":" + getDescription()+ "," +
            "stockQantity" + ":" + getStockQantity()+ "," +
            "price" + ":" + getPrice()+ "," +
            "title" + ":" + getTitle()+ "," +
            "category" + ":" + getCategory()+ "," +
            "gameID" + ":" + getGameID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "gamestatus" + "=" + (getGamestatus() != null ? !getGamestatus().equals(this)  ? getGamestatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "wishlist = "+(getWishlist()!=null?Integer.toHexString(System.identityHashCode(getWishlist())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "gameConsole = "+(getGameConsole()!=null?Integer.toHexString(System.identityHashCode(getGameConsole())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "promotion = "+(getPromotion()!=null?Integer.toHexString(System.identityHashCode(getPromotion())):"null");
  }
}