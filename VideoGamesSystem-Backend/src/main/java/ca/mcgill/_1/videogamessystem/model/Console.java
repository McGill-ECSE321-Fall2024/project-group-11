package ca.mcgill._1.videogamessystem.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

// line 104 "model.ump"
// line 216 "model.ump"
@Entity
public class Console
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Console Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  
  //Console Associations
  @OneToMany(mappedBy = "console")
  private List<GameConsole> gameConsoles = new ArrayList<>();
  

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Console(String consoleName) {
    name = consoleName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aConsole)
  {
    boolean wasSet = false;
    name = aConsole;
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


  public String getName()
  {
    return name;
  }

  /* Code from template association_GetMany */
  public Game getGame(int index)
  {
    GameConsole gc = gameConsoles.get(index);
    Game aGame = gc.getGame();
    return aGame;
  }

  public List<GameConsole> getGameConsoles() {
    return this.gameConsoles;
  }

  public List<Game> getGames() {
    List<GameConsole> GMs = new ArrayList<>();
    List<Game> games = new ArrayList<>();
    GMs = this.gameConsoles;
    for(GameConsole GM: GMs){
      games.add(GM.getGame());
    }
    return games;
  }

  public int numberOfGames()
  {
    return gameConsoles.size();
  }

  public boolean hasGames()
  {
    boolean has = gameConsoles.size() > 0;
    return has;
  }

  public int indexOfGame(Game aGame)
  {
    for(int i = 0; i < gameConsoles.size(); i++){
      if(gameConsoles.get(i).getGame().equals(aGame)){
        return i;
      }
    }
    return -1;
  }

  public static int minimumNumberOfGames()
  {
    return 0;
  }





  public boolean addGameConsole(GameConsole aGC)
  {
    boolean wasAdded = false;

    // Check if this GameConsole is already added to the list
    if (gameConsoles.contains(aGC)) {
      return false;
    }

    // Add GameConsole to the list in Console
    gameConsoles.add(aGC);

    // Check if the GameConsole already contains this Console
    if (aGC.getConsole().equals(this)) {
      wasAdded = true;
    } else {
      // Add this Console to the GameConsole entity
      aGC.setConsole(this);
      wasAdded = aGC.getGame().getConsoles().add(this);

      // If the Console wasn't added successfully, remove the GameConsole
      if (!wasAdded) {
        gameConsoles.remove(aGC);
      }
    }
    return wasAdded;
  }

  public boolean addGame(Game aGame){
    GameConsole aGC = new GameConsole(aGame,this);
    boolean wasAdded = false;
    wasAdded = this.addGameConsole(aGC) && aGame.addGameConsole(aGC);
    if(!wasAdded){
      this.removeGameConsole(aGC);
      aGame.removeGameConsole(aGC);
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
    if (aGC.getConsole().equals(this)) {
      wasRemoved = true; // It is correctly removed
    } else {
      // Try to remove this Console from the GameConsole object
      wasRemoved = aGC.getGame().getConsoles().remove(this);

      // If unsuccessful, re-add the GameConsole back to the list
      if (!wasRemoved) {
        gameConsoles.add(oldIndex, aGC);
      }
    }

    return wasRemoved;
  }

  public boolean removeGame(Game aGame){
    boolean wasRemoved = false;
    for(GameConsole GM: gameConsoles){
      if(GM.getGame().equals(aGame)){
        return this.removeGameConsole(GM) && aGame.removeGameConsole(GM);
      }
    }
    return wasRemoved;
  }


  public boolean addGameConsoleAt(GameConsole aGC, int index) {  
    boolean wasAdded = false;

    // Check if the GameConsole is added successfully using the addGameConsole method
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


  public boolean addGameAt(Game aGame, int index)
  {
    GameConsole aGC = new GameConsole(aGame, this);
    if(this.addGameConsoleAt(aGC, index) && aGC.getGame().addGameConsole(aGC)){
      return true;
    }
    this.removeGameConsole(aGC);
    aGame.removeGameConsole(aGC);
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
    } 
    else {
      // If not already in the list, add the GameConsole at the specified index
      wasAdded = addGameConsoleAt(aGC, index);
    }

    return wasAdded;
  }


  public boolean addOrMoveGameAt(Game aGame, int index){
    GameConsole aGC = new GameConsole(aGame, this);
    return this.addOrMoveGameConsoleAt(aGC, index);
  }


  public void delete()
  {
    ArrayList<GameConsole> copyOfGC = new ArrayList<GameConsole>(gameConsoles);
    gameConsoles.clear();
    for(GameConsole aGC : copyOfGC)
    {
      aGC.getGame().removeGameConsole(aGC);
      aGC.removeGame();
      aGC.removeConsole();
    }
  }

  public String toString()
  {
    return super.toString() + "["+
            "console" + ":" + getName()+ "]";
  }
}