/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;

// line 44 "model.ump"
// line 128 "model.ump"
public class Game
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private String title;
  private String description;
  private int stockQantity;
  private GameStatus gamestatus;
  private int price;
  private ConsoleType console;

  //Game Associations
  private List<Review> reviews;
  private List<Promotion> promotions;
  private List<SpecificGame> specificgame;
  private List<Wishlist> wishlists;
  private List<Cart> carts;
  private List<Category> categories;
  private List<Console> consoles;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(String aTitle, String aDescription, int aStockQantity, GameStatus aGamestatus, int aPrice, ConsoleType aConsole, Category... allCategories)
  {
    title = aTitle;
    description = aDescription;
    stockQantity = aStockQantity;
    gamestatus = aGamestatus;
    price = aPrice;
    console = aConsole;
    reviews = new ArrayList<Review>();
    promotions = new ArrayList<Promotion>();
    specificgame = new ArrayList<SpecificGame>();
    wishlists = new ArrayList<Wishlist>();
    carts = new ArrayList<Cart>();
    categories = new ArrayList<Category>();
    boolean didAddCategories = setCategories(allCategories);
    if (!didAddCategories)
    {
      throw new RuntimeException("Unable to create Game, must have at least 1 categories. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    consoles = new ArrayList<Console>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

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

  public boolean setConsole(ConsoleType aConsole)
  {
    boolean wasSet = false;
    console = aConsole;
    wasSet = true;
    return wasSet;
  }

  public String getTitle()
  {
    return title;
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

  public ConsoleType getConsole()
  {
    return console;
  }
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
  public Promotion getPromotion(int index)
  {
    Promotion aPromotion = promotions.get(index);
    return aPromotion;
  }

  public List<Promotion> getPromotions()
  {
    List<Promotion> newPromotions = Collections.unmodifiableList(promotions);
    return newPromotions;
  }

  public int numberOfPromotions()
  {
    int number = promotions.size();
    return number;
  }

  public boolean hasPromotions()
  {
    boolean has = promotions.size() > 0;
    return has;
  }

  public int indexOfPromotion(Promotion aPromotion)
  {
    int index = promotions.indexOf(aPromotion);
    return index;
  }
  /* Code from template association_GetMany */
  public SpecificGame getSpecificgame(int index)
  {
    SpecificGame aSpecificgame = specificgame.get(index);
    return aSpecificgame;
  }

  public List<SpecificGame> getSpecificgame()
  {
    List<SpecificGame> newSpecificgame = Collections.unmodifiableList(specificgame);
    return newSpecificgame;
  }

  public int numberOfSpecificgame()
  {
    int number = specificgame.size();
    return number;
  }

  public boolean hasSpecificgame()
  {
    boolean has = specificgame.size() > 0;
    return has;
  }

  public int indexOfSpecificgame(SpecificGame aSpecificgame)
  {
    int index = specificgame.indexOf(aSpecificgame);
    return index;
  }
  /* Code from template association_GetMany */
  public Wishlist getWishlist(int index)
  {
    Wishlist aWishlist = wishlists.get(index);
    return aWishlist;
  }

  public List<Wishlist> getWishlists()
  {
    List<Wishlist> newWishlists = Collections.unmodifiableList(wishlists);
    return newWishlists;
  }

  public int numberOfWishlists()
  {
    int number = wishlists.size();
    return number;
  }

  public boolean hasWishlists()
  {
    boolean has = wishlists.size() > 0;
    return has;
  }

  public int indexOfWishlist(Wishlist aWishlist)
  {
    int index = wishlists.indexOf(aWishlist);
    return index;
  }
  /* Code from template association_GetMany */
  public Cart getCart(int index)
  {
    Cart aCart = carts.get(index);
    return aCart;
  }

  public List<Cart> getCarts()
  {
    List<Cart> newCarts = Collections.unmodifiableList(carts);
    return newCarts;
  }

  public int numberOfCarts()
  {
    int number = carts.size();
    return number;
  }

  public boolean hasCarts()
  {
    boolean has = carts.size() > 0;
    return has;
  }

  public int indexOfCart(Cart aCart)
  {
    int index = carts.indexOf(aCart);
    return index;
  }
  /* Code from template association_GetMany */
  public Category getCategory(int index)
  {
    Category aCategory = categories.get(index);
    return aCategory;
  }

  public List<Category> getCategories()
  {
    List<Category> newCategories = Collections.unmodifiableList(categories);
    return newCategories;
  }

  public int numberOfCategories()
  {
    int number = categories.size();
    return number;
  }

  public boolean hasCategories()
  {
    boolean has = categories.size() > 0;
    return has;
  }

  public int indexOfCategory(Category aCategory)
  {
    int index = categories.indexOf(aCategory);
    return index;
  }
  /* Code from template association_GetMany */
  public Console getConsole(int index)
  {
    Console aConsole = consoles.get(index);
    return aConsole;
  }

  public List<Console> getConsoles()
  {
    List<Console> newConsoles = Collections.unmodifiableList(consoles);
    return newConsoles;
  }

  public int numberOfConsoles()
  {
    int number = consoles.size();
    return number;
  }

  public boolean hasConsoles()
  {
    boolean has = consoles.size() > 0;
    return has;
  }

  public int indexOfConsole(Console aConsole)
  {
    int index = consoles.indexOf(aConsole);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReviews()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Review addReview(int aRating, String aContent, Customer aCustomer)
  {
    return new Review(aRating, aContent, aCustomer, this);
  }

  public boolean addReview(Review aReview)
  {
    boolean wasAdded = false;
    if (reviews.contains(aReview)) { return false; }
    Game existingGame = aReview.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aReview.setGame(this);
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
    //Unable to remove aReview, as it must always have a game
    if (!this.equals(aReview.getGame()))
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
  public static int minimumNumberOfPromotions()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addPromotion(Promotion aPromotion)
  {
    boolean wasAdded = false;
    if (promotions.contains(aPromotion)) { return false; }
    promotions.add(aPromotion);
    if (aPromotion.indexOfGame(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPromotion.addGame(this);
      if (!wasAdded)
      {
        promotions.remove(aPromotion);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removePromotion(Promotion aPromotion)
  {
    boolean wasRemoved = false;
    if (!promotions.contains(aPromotion))
    {
      return wasRemoved;
    }

    int oldIndex = promotions.indexOf(aPromotion);
    promotions.remove(oldIndex);
    if (aPromotion.indexOfGame(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPromotion.removeGame(this);
      if (!wasRemoved)
      {
        promotions.add(oldIndex,aPromotion);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPromotionAt(Promotion aPromotion, int index)
  {  
    boolean wasAdded = false;
    if(addPromotion(aPromotion))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPromotions()) { index = numberOfPromotions() - 1; }
      promotions.remove(aPromotion);
      promotions.add(index, aPromotion);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePromotionAt(Promotion aPromotion, int index)
  {
    boolean wasAdded = false;
    if(promotions.contains(aPromotion))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPromotions()) { index = numberOfPromotions() - 1; }
      promotions.remove(aPromotion);
      promotions.add(index, aPromotion);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPromotionAt(aPromotion, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpecificgame()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpecificGame addSpecificgame(int aSerialNumber)
  {
    return new SpecificGame(aSerialNumber, this);
  }

  public boolean addSpecificgame(SpecificGame aSpecificgame)
  {
    boolean wasAdded = false;
    if (specificgame.contains(aSpecificgame)) { return false; }
    Game existingGame = aSpecificgame.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aSpecificgame.setGame(this);
    }
    else
    {
      specificgame.add(aSpecificgame);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSpecificgame(SpecificGame aSpecificgame)
  {
    boolean wasRemoved = false;
    //Unable to remove aSpecificgame, as it must always have a game
    if (!this.equals(aSpecificgame.getGame()))
    {
      specificgame.remove(aSpecificgame);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSpecificgameAt(SpecificGame aSpecificgame, int index)
  {  
    boolean wasAdded = false;
    if(addSpecificgame(aSpecificgame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificgame()) { index = numberOfSpecificgame() - 1; }
      specificgame.remove(aSpecificgame);
      specificgame.add(index, aSpecificgame);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSpecificgameAt(SpecificGame aSpecificgame, int index)
  {
    boolean wasAdded = false;
    if(specificgame.contains(aSpecificgame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificgame()) { index = numberOfSpecificgame() - 1; }
      specificgame.remove(aSpecificgame);
      specificgame.add(index, aSpecificgame);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSpecificgameAt(aSpecificgame, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWishlists()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addWishlist(Wishlist aWishlist)
  {
    boolean wasAdded = false;
    if (wishlists.contains(aWishlist)) { return false; }
    wishlists.add(aWishlist);
    if (aWishlist.indexOfGame(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aWishlist.addGame(this);
      if (!wasAdded)
      {
        wishlists.remove(aWishlist);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeWishlist(Wishlist aWishlist)
  {
    boolean wasRemoved = false;
    if (!wishlists.contains(aWishlist))
    {
      return wasRemoved;
    }

    int oldIndex = wishlists.indexOf(aWishlist);
    wishlists.remove(oldIndex);
    if (aWishlist.indexOfGame(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aWishlist.removeGame(this);
      if (!wasRemoved)
      {
        wishlists.add(oldIndex,aWishlist);
      }
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
      if(index > numberOfWishlists()) { index = numberOfWishlists() - 1; }
      wishlists.remove(aWishlist);
      wishlists.add(index, aWishlist);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWishlistAt(Wishlist aWishlist, int index)
  {
    boolean wasAdded = false;
    if(wishlists.contains(aWishlist))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWishlists()) { index = numberOfWishlists() - 1; }
      wishlists.remove(aWishlist);
      wishlists.add(index, aWishlist);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWishlistAt(aWishlist, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCarts()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addCart(Cart aCart)
  {
    boolean wasAdded = false;
    if (carts.contains(aCart)) { return false; }
    carts.add(aCart);
    if (aCart.indexOfGame(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aCart.addGame(this);
      if (!wasAdded)
      {
        carts.remove(aCart);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeCart(Cart aCart)
  {
    boolean wasRemoved = false;
    if (!carts.contains(aCart))
    {
      return wasRemoved;
    }

    int oldIndex = carts.indexOf(aCart);
    carts.remove(oldIndex);
    if (aCart.indexOfGame(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aCart.removeGame(this);
      if (!wasRemoved)
      {
        carts.add(oldIndex,aCart);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCartAt(Cart aCart, int index)
  {  
    boolean wasAdded = false;
    if(addCart(aCart))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCarts()) { index = numberOfCarts() - 1; }
      carts.remove(aCart);
      carts.add(index, aCart);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCartAt(Cart aCart, int index)
  {
    boolean wasAdded = false;
    if(carts.contains(aCart))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCarts()) { index = numberOfCarts() - 1; }
      carts.remove(aCart);
      carts.add(index, aCart);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCartAt(aCart, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfCategoriesValid()
  {
    boolean isValid = numberOfCategories() >= minimumNumberOfCategories();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCategories()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addCategory(Category aCategory)
  {
    boolean wasAdded = false;
    if (categories.contains(aCategory)) { return false; }
    categories.add(aCategory);
    if (aCategory.indexOfGame(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aCategory.addGame(this);
      if (!wasAdded)
      {
        categories.remove(aCategory);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeCategory(Category aCategory)
  {
    boolean wasRemoved = false;
    if (!categories.contains(aCategory))
    {
      return wasRemoved;
    }

    if (numberOfCategories() <= minimumNumberOfCategories())
    {
      return wasRemoved;
    }

    int oldIndex = categories.indexOf(aCategory);
    categories.remove(oldIndex);
    if (aCategory.indexOfGame(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aCategory.removeGame(this);
      if (!wasRemoved)
      {
        categories.add(oldIndex,aCategory);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setCategories(Category... newCategories)
  {
    boolean wasSet = false;
    ArrayList<Category> verifiedCategories = new ArrayList<Category>();
    for (Category aCategory : newCategories)
    {
      if (verifiedCategories.contains(aCategory))
      {
        continue;
      }
      verifiedCategories.add(aCategory);
    }

    if (verifiedCategories.size() != newCategories.length || verifiedCategories.size() < minimumNumberOfCategories())
    {
      return wasSet;
    }

    ArrayList<Category> oldCategories = new ArrayList<Category>(categories);
    categories.clear();
    for (Category aNewCategory : verifiedCategories)
    {
      categories.add(aNewCategory);
      if (oldCategories.contains(aNewCategory))
      {
        oldCategories.remove(aNewCategory);
      }
      else
      {
        aNewCategory.addGame(this);
      }
    }

    for (Category anOldCategory : oldCategories)
    {
      anOldCategory.removeGame(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCategoryAt(Category aCategory, int index)
  {  
    boolean wasAdded = false;
    if(addCategory(aCategory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCategories()) { index = numberOfCategories() - 1; }
      categories.remove(aCategory);
      categories.add(index, aCategory);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCategoryAt(Category aCategory, int index)
  {
    boolean wasAdded = false;
    if(categories.contains(aCategory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCategories()) { index = numberOfCategories() - 1; }
      categories.remove(aCategory);
      categories.add(index, aCategory);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCategoryAt(aCategory, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfConsoles()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addConsole(Console aConsole)
  {
    boolean wasAdded = false;
    if (consoles.contains(aConsole)) { return false; }
    consoles.add(aConsole);
    if (aConsole.indexOfGame(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aConsole.addGame(this);
      if (!wasAdded)
      {
        consoles.remove(aConsole);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeConsole(Console aConsole)
  {
    boolean wasRemoved = false;
    if (!consoles.contains(aConsole))
    {
      return wasRemoved;
    }

    int oldIndex = consoles.indexOf(aConsole);
    consoles.remove(oldIndex);
    if (aConsole.indexOfGame(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aConsole.removeGame(this);
      if (!wasRemoved)
      {
        consoles.add(oldIndex,aConsole);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addConsoleAt(Console aConsole, int index)
  {  
    boolean wasAdded = false;
    if(addConsole(aConsole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfConsoles()) { index = numberOfConsoles() - 1; }
      consoles.remove(aConsole);
      consoles.add(index, aConsole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveConsoleAt(Console aConsole, int index)
  {
    boolean wasAdded = false;
    if(consoles.contains(aConsole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfConsoles()) { index = numberOfConsoles() - 1; }
      consoles.remove(aConsole);
      consoles.add(index, aConsole);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addConsoleAt(aConsole, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (reviews.size() > 0)
    {
      Review aReview = reviews.get(reviews.size() - 1);
      aReview.delete();
      reviews.remove(aReview);
    }
    
    ArrayList<Promotion> copyOfPromotions = new ArrayList<Promotion>(promotions);
    promotions.clear();
    for(Promotion aPromotion : copyOfPromotions)
    {
      aPromotion.removeGame(this);
    }
    while (specificgame.size() > 0)
    {
      SpecificGame aSpecificgame = specificgame.get(specificgame.size() - 1);
      aSpecificgame.delete();
      specificgame.remove(aSpecificgame);
    }
    
    ArrayList<Wishlist> copyOfWishlists = new ArrayList<Wishlist>(wishlists);
    wishlists.clear();
    for(Wishlist aWishlist : copyOfWishlists)
    {
      aWishlist.removeGame(this);
    }
    ArrayList<Cart> copyOfCarts = new ArrayList<Cart>(carts);
    carts.clear();
    for(Cart aCart : copyOfCarts)
    {
      aCart.removeGame(this);
    }
    ArrayList<Category> copyOfCategories = new ArrayList<Category>(categories);
    categories.clear();
    for(Category aCategory : copyOfCategories)
    {
      aCategory.removeGame(this);
    }
    ArrayList<Console> copyOfConsoles = new ArrayList<Console>(consoles);
    consoles.clear();
    for(Console aConsole : copyOfConsoles)
    {
      aConsole.removeGame(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "," +
            "description" + ":" + getDescription()+ "," +
            "stockQantity" + ":" + getStockQantity()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "gamestatus" + "=" + (getGamestatus() != null ? !getGamestatus().equals(this)  ? getGamestatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "console" + "=" + (getConsole() != null ? !getConsole().equals(this)  ? getConsole().toString().replaceAll("  ","    ") : "this" : "null");
  }
}