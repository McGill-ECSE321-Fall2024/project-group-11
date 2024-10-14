package ca.mcgill.ecse321.videogamessystem.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

// line 44 "model.ump"
// line 121 "model.ump"
@Entity
public class Game
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String description;
  private int stockQantity;
  private GameStatus status;
  private int price;

  public enum GameStatus {
    ACTIVE,
    INACTIVE,
    PENDING,
    ARCHIVED
  }

  //Game Associations
  @OneToMany
  private List<Review> reviews;
  @OneToMany
  private List<Promotion> promotions;
  @OneToMany
  private List<SpecificGame> specificGames;
  @OneToMany
  private List<Customer> wishlist;
  @OneToMany
  private List<Customer> cart;
  @OneToMany
  private List<Category> categories;
  @OneToMany
  private List<GameConsole> gameConsoles;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(String aTitle, String aDescription, int aStockQantity, GameStatus aStatus, int aPrice, Category... allCategories)
  {
    title = aTitle;
    description = aDescription;
    stockQantity = aStockQantity;
    status = aStatus;
    price = aPrice;
    gameConsoles = new ArrayList<>();
    reviews = new ArrayList<Review>();
    promotions = new ArrayList<Promotion>();
    specificGames = new ArrayList<SpecificGame>();
    wishlist = new ArrayList<Customer>();
    cart = new ArrayList<Customer>();
    categories = new ArrayList<Category>();
    boolean didAddCategories = setCategories(allCategories);
    if (!didAddCategories)
    {
      throw new RuntimeException("Unable to create Game, must have at least 1 categories. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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

  public boolean setStatus(GameStatus aStatus)
  {
    boolean wasSet = false;
    status = aStatus;
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

  // Getters and setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public GameStatus getStatus()
  {
    return status;
  }

  public int getPrice()
  {
    return price;
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
  /* Code from template association_GetMany */
  public Customer getWishlist(int index)
  {
    Customer aWishlist = wishlist.get(index);
    return aWishlist;
  }

  public List<Customer> getWishlist()
  {
    List<Customer> newWishlist = Collections.unmodifiableList(wishlist);
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

  public int indexOfWishlist(Customer aWishlist)
  {
    int index = wishlist.indexOf(aWishlist);
    return index;
  }
  /* Code from template association_GetMany */
  public Customer getCart(int index)
  {
    Customer aCart = cart.get(index);
    return aCart;
  }

  public List<Customer> getCart()
  {
    List<Customer> newCart = Collections.unmodifiableList(cart);
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

  public int indexOfCart(Customer aCart)
  {
    int index = cart.indexOf(aCart);
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
    GameConsole gc = gameConsoles.get(index);
    Console aConsole = gc.getConsole();
    return aConsole;
  }

  public List<GameConsole> getGameConsoles() {
    return this.gameConsoles;
  }

  public List<Console> getConsoles() {
    List<GameConsole> GMs = new ArrayList<>();
    List<Console> consoles = new ArrayList<>();
    GMs = this.gameConsoles;
    for (GameConsole GM : GMs) {
      consoles.add(GM.getConsole());
    }
    return consoles;
  }

  public int numberOfConsoles()
  {
    int number = gameConsoles.size();
    return number;
  }

  public boolean hasConsoles()
  {
    boolean has = gameConsoles.size() > 0;
    return has;
  }

  public int indexOfConsole(Console aConsole)
  {
    for (int i = 0; i < gameConsoles.size(); i++) {
      if (gameConsoles.get(i).getConsole().equals(aConsole)) {
        return i;
      }
    }
    return -1;
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
  public static int minimumNumberOfSpecificGames()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpecificGame addSpecificGame(int aSerialNumber)
  {
    return new SpecificGame(aSerialNumber, this);
  }

  public boolean addSpecificGame(SpecificGame aSpecificGame)
  {
    boolean wasAdded = false;
    if (specificGames.contains(aSpecificGame)) { return false; }
    Game existingGame = aSpecificGame.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aSpecificGame.setGame(this);
    }
    else
    {
      specificGames.add(aSpecificGame);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSpecificGame(SpecificGame aSpecificGame)
  {
    boolean wasRemoved = false;
    //Unable to remove aSpecificGame, as it must always have a game
    if (!this.equals(aSpecificGame.getGame()))
    {
      specificGames.remove(aSpecificGame);
      wasRemoved = true;
    }
    return wasRemoved;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWishlist()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addWishlist(Customer aWishlist)
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
  public boolean removeWishlist(Customer aWishlist)
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
  public boolean addWishlistAt(Customer aWishlist, int index)
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

  public boolean addOrMoveWishlistAt(Customer aWishlist, int index)
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
  public boolean addCart(Customer aCart)
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
  public boolean removeCart(Customer aCart)
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
  public boolean addCartAt(Customer aCart, int index)
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

  public boolean addOrMoveCartAt(Customer aCart, int index)
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


  public boolean addGameConsole(GameConsole aGC) {
    boolean wasAdded = false;

    // Check if this GameConsole is already added to the list
    if (gameConsoles.contains(aGC)) {
      return false;
    }

    // Add GameConsole to the list in Console
    gameConsoles.add(aGC);

    // Check if the GameConsole already contains this Console
    if (aGC.getGame().equals(this)) {
      wasAdded = true;
    } else {
      // Add this Console to the GameConsole entity
      aGC.setGame(this);
      wasAdded = aGC.getConsole().getGames().add(this);

      // If the Console wasn't added successfully, remove the GameConsole
      if (!wasAdded) {
        gameConsoles.remove(aGC);
      }
    }
    return wasAdded;
  }

  /* Code from template association_AddManyToManyMethod */
  public boolean addConsole(Console aConsole)
  {
    GameConsole aGC = new GameConsole(this, aConsole);
    boolean wasAdded = false;
    wasAdded = this.addGameConsole(aGC) && aConsole.addGameConsole(aGC);
    if (!wasAdded) {
      this.removeGameConsole(aGC);
      aConsole.removeGameConsole(aGC);
    }
    return wasAdded;
  }



  public boolean removeGameConsole(GameConsole aGC) {
    boolean wasRemoved = false;

    // Check if the GameConsole is in the gameConsoles list
    if (!gameConsoles.contains(aGC)) {
      return wasRemoved; // If it's not there, return false
    }

    // Remove GameConsole from the list
    int oldIndex = gameConsoles.indexOf(aGC);
    gameConsoles.remove(oldIndex);

    // Check if this Console is still referenced in the GameConsole
    if (aGC.getGame().equals(this)) {
      wasRemoved = true; // It is correctly removed
    } else {
      // Try to remove this Console from the GameConsole object
      wasRemoved = aGC.getConsole().getGames().remove(this);

      // If unsuccessful, re-add the GameConsole back to the list
      if (!wasRemoved) {
        gameConsoles.add(oldIndex, aGC);
      }
    }

    return wasRemoved;
  }

  /* Code from template association_RemoveMany */
  public boolean removeConsole(Console aConsole){
    boolean wasRemoved = false;
    for (GameConsole GM : gameConsoles) {
      if (GM.getConsole().equals(aConsole)) {
        return this.removeGameConsole(GM) && aConsole.removeGameConsole(GM);
      }
    }
    return wasRemoved;
  }

  public boolean addGameConsoleAt(GameConsole aGC, int index) {
    boolean wasAdded = false;

    // Check if the GameConsole is added successfully using the addGameConsole
    // method
    if (addGameConsole(aGC)) {
      // Ensure index is within the valid range
      if (index < 0) {
        index = 0;
      }
      if (index > gameConsoles.size()) {
        index = gameConsoles.size();
      }

      // Remove and re-add GameConsole at the specified index
      gameConsoles.remove(aGC);
      gameConsoles.add(index, aGC);
      wasAdded = true;
    }

    return wasAdded;
  }



  public boolean addConsoleAt(Console aConsole, int index) {
    GameConsole aGC = new GameConsole(this, aConsole);
    if (this.addGameConsoleAt(aGC, index) && aGC.getConsole().addGameConsole(aGC)) {
      return true;
    }
    this.removeGameConsole(aGC);
    aConsole.removeGameConsole(aGC);
    return false;
  }

  public boolean addOrMoveGameConsoleAt(GameConsole aGC, int index) {
    boolean wasAdded = false;

    // Check if the GameConsole is already present in the list
    if (gameConsoles.contains(aGC)) {
      // Ensure index is within the valid range
      if (index < 0) {
        index = 0;
      }
      if (index > gameConsoles.size()) {
        index = gameConsoles.size();
      }
      // Remove and re-add GameConsole at the specified index
      gameConsoles.remove(aGC);
      gameConsoles.add(index, aGC);
      wasAdded = true;
    } else {
      // If not already in the list, add the GameConsole at the specified index
      wasAdded = addGameConsoleAt(aGC, index);
    }

    return wasAdded;
  }



  public boolean addOrMoveConsoleAt(Console aConsole, int index)
  {
    GameConsole aGC = new GameConsole(this, aConsole);
    return this.addOrMoveGameConsoleAt(aGC, index);
  }

  public void delete()
  {
    for(int i=reviews.size(); i > 0; i--)
    {
      Review aReview = reviews.get(i - 1);
      aReview.delete();
    }
    ArrayList<Promotion> copyOfPromotions = new ArrayList<Promotion>(promotions);
    promotions.clear();
    for(Promotion aPromotion : copyOfPromotions)
    {
      aPromotion.removeGame(this);
    }
    for(int i=specificGames.size(); i > 0; i--)
    {
      SpecificGame aSpecificGame = specificGames.get(i - 1);
      aSpecificGame.delete();
    }
    ArrayList<Customer> copyOfWishlist = new ArrayList<Customer>(wishlist);
    wishlist.clear();
    for(Customer aWishlist : copyOfWishlist)
    {
      aWishlist.removeWishlist(this);
    }
    ArrayList<Customer> copyOfCart = new ArrayList<Customer>(cart);
    cart.clear();
    for(Customer aCart : copyOfCart)
    {
      aCart.removeCart(this);
    }
    ArrayList<Category> copyOfCategories = new ArrayList<Category>(categories);
    categories.clear();
    for(Category aCategory : copyOfCategories)
    {
      aCategory.removeGame(this);
    }

    ArrayList<GameConsole> copyOfGC = new ArrayList<GameConsole>(gameConsoles);
    gameConsoles.clear();
    for (GameConsole aGC : copyOfGC) {
      aGC.getConsole().removeGameConsole(aGC);
      aGC.removeConsole();
      aGC.removeGame();
    }

  }


  public String toString()
  {
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "," +
            "description" + ":" + getDescription()+ "," +
            "stockQantity" + ":" + getStockQantity()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "gamestatus" + "=" + (getStatus() != null ? !getStatus().equals(this)  ? getStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator");
  }
}