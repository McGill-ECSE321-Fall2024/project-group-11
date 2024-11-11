package ca.mcgill.ecse321.videogamessystem.model;

// line 57 "model.ump"
// line 143 "model.ump"
public enum Category {
    Adventure,
    Action,
    Sports,
    Strategy,
    Puzzle,
    Party,
    Survival,
    Arcade,
    Other
    ;
    
    @Override
    public String toString() {
        return name();
    }
}