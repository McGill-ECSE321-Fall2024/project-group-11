package ca.mcgill.ecse321.videogamessystem.dto.GameDto;

import ca.mcgill.ecse321.videogamessystem.model.Game.Category;
import ca.mcgill.ecse321.videogamessystem.model.Game;
import ca.mcgill.ecse321.videogamessystem.model.Game.ConsoleType;
// pb with 
public class GameSummaryDto {

     private String title;
    private String description;
    private int price;
    private Category category;
    private ConsoleType consoleType;

    public GameSummaryDto(Game game){

        this.title = game.getTitle();
        this.description = game.getDescription();
        this.price = game.getPrice();
        this.category = game.getCategory();
        this.consoleType = game.getConsoleType();
    }
    protected GameSummaryDto() {}

    public String GetTitle(){return title;}

    public String GetDescription(){return description;}

    public int GetPrice(){return price;}

    public Category GetCategory(){return category;}

    public ConsoleType GetConsoleType(){return consoleType;}


    
    
}
