/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



// line 11 "model.ump"
// line 178 "model.ump"
public class Game
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private String description;
  private int stockQantity;
  private int price;
  private String title;
  private Category category;

  //Game Associations
  private Wishlist wishlist;
  private GameConsole gameConsole;
  private Promotion promotion;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(String aDescription, int aStockQantity, int aPrice, String aTitle, Category aCategory, Wishlist aWishlist, GameConsole aGameConsole, Promotion aPromotion)
  {
    description = aDescription;
    stockQantity = aStockQantity;
    price = aPrice;
    title = aTitle;
    category = aCategory;
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

  public boolean setCategory(Category aCategory)
  {
    boolean wasSet = false;
    category = aCategory;
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

  public int getPrice()
  {
    return price;
  }

  public String getTitle()
  {
    return title;
  }

  public Category getCategory()
  {
    return category;
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
            "title" + ":" + getTitle()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "category" + "=" + (getCategory() != null ? !getCategory().equals(this)  ? getCategory().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "wishlist = "+(getWishlist()!=null?Integer.toHexString(System.identityHashCode(getWishlist())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "gameConsole = "+(getGameConsole()!=null?Integer.toHexString(System.identityHashCode(getGameConsole())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "promotion = "+(getPromotion()!=null?Integer.toHexString(System.identityHashCode(getPromotion())):"null");
  }
}